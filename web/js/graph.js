$(document).ready(function () {

	var margin = {top: 10, right: 20, bottom:30, left: 20},
		width,
		height;

	var svg;

	d3.json("ajaxGetDevelepers", createChart);

	function createChart(data) {
		var name = [],ids = [],
			charts = [],
			maxDataPoint = 0;

		for (var index in data.commiters) {
			name.push(data.commiters[index].name);
			ids.push(data.commiters[index].id)
		}

		width = 1200 - margin.left - margin.right,
		height = name.length*40;

		svg = d3.select("#chart-container")
			.append("svg")
			.attr("width", width + margin.left + margin.right)
			.attr("height", (height + margin.top + margin.bottom));

		data.intervals.forEach(function (d) {
			for (var prop in d) {
				if (d.hasOwnProperty(prop)) {
					d[prop] = parseInt(d[prop]);

					if (d[prop] > maxDataPoint) {
						maxDataPoint = d[prop];
					}
				}
			}

			// D3 needs a date object, let's convert it just one time
			d.Date = new Date(2014,4,d.Date);
		});

		for (var i = 0; i < name.length; i++) {
			charts.push(new Chart({
				data: data.intervals.slice(), // copy the array
				id: i,
				name: name[i],
				ids: ids[i],
				width: width,
				height: height * (1 / name.length),
				maxDataPoint: maxDataPoint,
				svg: svg,
				margin: margin,
				showBottomAxis: (i == name.length - 1)
			}));
		}
	}


	function Chart(options) {
		var chartData = options.data;
		width = options.width;
		var height = options.height;
		//this.maxDataPoint = options.maxDataPoint;
		var maxDataPoint = 10;
		var svg = options.svg;
		var id = options.id;
		var name = options.name;
		var ids = options.ids;
		var margin = options.margin;
		var showBottomAxis = options.showBottomAxis;

		/* XScale is time based */
		var xScale = d3.time.scale()
			.range([0, width])
			.domain(d3.extent(chartData.map(function (d) {
				return d.Date;
			})));

		/* YScale is linear based on the maxData Point we found earlier*/
		var yScale = d3.scale.linear()
			.range([height, 0])
			.domain([0, maxDataPoint]);

		var xS = xScale;
		var yS = yScale;

		var area = d3.svg.area()
			.interpolate("basis")
			.x(function (d) {
				return xS(d.Date);
			})
			.y0(height)
			.y1(function (d) {
				return yS(d[ids]);
			});

		svg.append("defs").append("clipPath")
			.attr("id", "clip-" + id)
			.append("rect")
			.attr("width", width)
			.attr("height", height)
			.attr("class","area")
		;

		var chartContainer = svg.append("g")
			.attr("transform", "translate(" + margin.left + "," + (margin.top + (height * id) + (3 * id)) + ")");

		/* We've created everything, let's actually add it to the page */

		chartContainer.append("path")
			.data([chartData])
			.attr('class', "graph graph"+ids )
			.attr("clip-path", "url(#clip-" + id + ")")
			.attr("d", area);

		var xAxisTop = d3.svg.axis().scale(xScale).orient("bottom");
		var xAxisBottom = d3.svg.axis().scale(xScale).orient("top");

		/* We only want a top axis if it's the first country

		if (id == 0) {
			chartContainer.append("g")
				.attr("class", "x axis top")
				.attr("transform", "translate(0,0)")
				.call(xAxisTop);
		}
		*/
		/* Only want a bottom axis on the last country */

		if (showBottomAxis) {
			chartContainer.append("g")
				.attr("class", "x axis bottom")
				.attr("transform", "translate(0," + height + ")")
				.call(xAxisBottom);
		}

		/*yAxis = d3.svg.axis().scale(yScale).orient("left").ticks(5);

		 chartContainer.append("g")
		 .attr("class", "y axis")
		 .attr("transform", "translate(-15,0)")
		 .call(yAxis);
		 */

		chartContainer.append("text")
			.attr("class", "name-title")
			.attr("transform", "translate(15,20)")
			.text(name);

		svg
			.on("mouseover", function () {
				svg.transition()
					.attr("width", 1200)
					.duration(1000)
					.ease("elastic");
				d3.select("#chart-container").transition()
					.attr("width", 1200)
					.duration(1000)
					.ease("elastic");
			})
			.on("mouseleave", function () {
				svg.transition()
					.attr("width", 220)
					.duration(500);
				d3.select("#chart-container").transition()
					.attr("width", 220)
					.duration(500);
			});

		svg
			.attr("width", 220);
		d3.select("#chart-container")
			.attr("width", 220);
	}
});