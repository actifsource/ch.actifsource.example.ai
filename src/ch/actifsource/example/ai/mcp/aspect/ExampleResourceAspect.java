package ch.actifsource.example.ai.mcp.aspect;

import ch.actifsource.access.server.service.request.mcp.schema.IMcpResource;
import ch.actifsource.access.server.service.request.mcp.schema.IMcpResourceAspect;
import ch.actifsource.core.job.IReadJobExecutor;
import ch.actifsource.util.ICancelStatus;
import ch.actifsource.util.json.util.JsonUtil;
import ch.actifsource.util.json.util.JsonUtil.JsonObject;
import ch.actifsource.access.server.mcp.model.ModelPackage;
import ch.actifsource.access.server.service.JsonMessageId;
import ch.actifsource.core.job.Select;
import ch.actifsource.core.util.LiteralUtil;

public class ExampleResourceAspect implements IMcpResourceAspect {

  @Override
  public ResourceList list(IReadJobExecutor executor, IMcpResource resource, String cursor, ICancelStatus cancelStatus) {
    JsonObject resourceContentAsJson = JsonUtil.createNewJsonObject();
    resourceContentAsJson.appendValue("uri" /*or uriTemplate*/, "example://placeholder");
    resourceContentAsJson.appendValue(JsonMessageId.KEY_MCP_NAME, "Example Resource");
    resourceContentAsJson.appendValue(JsonMessageId.KEY_MCP_DESCRIPTION, LiteralUtil.getStringValue(Select.objectForAttributeOrNull(executor, ModelPackage.BaseMcpRequest_description, resource.getResourceNode()), ""));
    resourceContentAsJson.appendValue(JsonMessageId.KEY_MCP_MIME_TYPE, "text/markdown");
    resourceContentAsJson.setUnmodifiable();
    return new ResourceList(resourceContentAsJson);  
  }

  @Override
  public JsonObject read(IReadJobExecutor readJobExecutor, IMcpResource resource, String uri, ICancelStatus cancelStatus) {
    JsonObject response = JsonUtil.createNewJsonObject();
    response.appendValue("uri", uri);
    response.appendValue("mimeType", "text/plain");
    response.appendValue("text", "Example resource content for URI '" + uri + "' replace this with the real implementation.");
    response.setUnmodifiable();
    return response;
  }
}
