package sk.BusinessLogic.JsonConverter;

import org.datacontract.schemas._2004._07.gratex_perconik_astrcs_svc.ChangesetDto;
import org.datacontract.schemas._2004._07.gratex_perconik_astrcs_svc.FileVersionDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pipo
 * Date: 5.3.2014
 * Time: 16:30
 */


public class TransformToJson  {

    public JSONObject projectTreeToJson(List<FileVersionDto> fileVersionDtoList) {

        Collections.sort(fileVersionDtoList,FileVersionDto.Comparators.URL);

        JSONObject jsonObject = new JSONObject();
        PathTreeCreator pathTreeCreator = new PathTreeCreator(jsonObject);

        for(FileVersionDto fileVersionDto : fileVersionDtoList) {
            pathTreeCreator.addPath(fileVersionDto.getUrl().getValue());
        }

        pathTreeCreator.buildJsonTree();

        return pathTreeCreator.getJsonObject();
    }

    public JSONArray changesetListToJson(List<ChangesetDto> changesetDtoList) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (ChangesetDto changesetDto : changesetDtoList)
        {
            jsonArray.add(changesetDto.getId());
        }

        jsonObject.put("id", jsonArray);

        return jsonArray;
    }

}
