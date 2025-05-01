package com.demo.mcpserver;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.ServerCapabilities;

public class MyMcpService {

    McpSyncServer mcpServer;

    public McpSyncServer getServer(MyTransportProvider transportProvider) {
 
        mcpServer = McpServer.sync(transportProvider)
        .serverInfo("my-mcp-server", "0.0.1")
        .capabilities(
            ServerCapabilities.builder()
            .tools(true)
            .resources(false, false)
            .prompts(false)
            .build())
        .tools(getToolgetMarsWeather())
        .build();

        return mcpServer;
    }

    private McpServerFeatures.SyncToolSpecification getToolgetMarsWeather() {

        McpSchema.Tool tool = new McpSchema.Tool(
            "mars-weather",
            "gives today's weather of mars for a given cotinent written in English.",
            """
            {
              "type": "object",
              "properties": {
                "continent": {
                  "type": "string"
                }
              },
              "required": ["continent"]
            }
            """
        );                

        return new McpServerFeatures.SyncToolSpecification(tool, (exchange, args) -> {

           String continent = args.get("continent").toString();
           Map<String, String> marsWeather = MarsLogic.getMarsWeather(continent);

          ObjectMapper objectMapper = new ObjectMapper();
           String result;
           try{
               result = objectMapper.writeValueAsString(marsWeather);
           } catch (Exception e) {
               result = "";
               e.printStackTrace();
           }
           McpSchema.Content content = new McpSchema.TextContent(result);

           return new McpSchema.CallToolResult(List.of(content), false);

        });
    }

}
