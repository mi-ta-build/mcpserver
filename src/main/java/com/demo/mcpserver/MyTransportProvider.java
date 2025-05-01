package com.demo.mcpserver;

import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.transport.HttpServletSseServerTransportProvider;

import jakarta.servlet.annotation.WebServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"*"}, asyncSupported = true)
public class MyTransportProvider extends HttpServletSseServerTransportProvider {

	McpSyncServer server;

	public MyTransportProvider() {
		super(new ObjectMapper(), "/message", "/sse");
		server= new MyMcpService().getServer(this);
	}

}
