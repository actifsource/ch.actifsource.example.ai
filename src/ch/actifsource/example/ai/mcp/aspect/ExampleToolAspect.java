package ch.actifsource.example.ai.mcp.aspect;

import ch.actifsource.access.server.service.request.mcp.schema.IMcpTool;
import ch.actifsource.access.server.service.request.mcp.schema.IMcpToolAspect;
import ch.actifsource.access.server.service.request.mcp.util.McpUtil;
import ch.actifsource.core.Resource;
import ch.actifsource.core.job.IWriteJobExecutor;
import ch.actifsource.util.ICancelStatus;
import ch.actifsource.util.json.util.JsonUtil;
import ch.actifsource.util.json.util.JsonUtil.JsonObject;

public class ExampleToolAspect implements IMcpToolAspect {

  @Override
  public JsonObject call(IWriteJobExecutor writeJobExecutor, IMcpTool tool, JsonObject input, ICancelStatus cancelStatus) {
    Resource resource = McpUtil.getResourceFromJson("resource_guid", input);
    if (resource == null) {
      return McpUtil.createResponseErrorContent("Argument 'resource_guid' is missing or not a valid GUID.");
    }
    JsonObject response = JsonUtil.createNewJsonObject();
    response.appendValue("type", "text");
    response.appendValue("text", "Example tool invoked with resource_guid '" + resource.toString() + "' — replace this with the real implementation.");
    response.setUnmodifiable();
    return response;
  }
}
