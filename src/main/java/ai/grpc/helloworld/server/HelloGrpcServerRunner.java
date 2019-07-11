package ai.grpc.helloworld.server;

import java.io.IOException;

import io.grpc.BindableService;

public class HelloGrpcServerRunner {

	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 54321;

		BindableService helloService = new HelloGrpcServerService();
		HelloGrpcServer server = new HelloGrpcServer(port, helloService);

		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
	}
}
