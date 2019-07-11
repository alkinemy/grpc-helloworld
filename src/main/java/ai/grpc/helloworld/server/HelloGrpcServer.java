package ai.grpc.helloworld.server;

import java.io.IOException;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloGrpcServer {

	private final int port;
	private final Server server;

	public HelloGrpcServer(int port, BindableService service) {
		this.port = port;
		this.server = ServerBuilder.forPort(port)
			.addService(service)
			.build();
	}

	public void start() throws IOException, InterruptedException {
		this.server.start();
		log.info("트럼프가 {}포트에서 리스닝 중...", port);
		this.server.awaitTermination();
	}

	public void shutdown() {
		log.error("트럼프 서버 종료...");
		server.shutdown();
		log.error("트럼프 서버 종료 완료");
	}
}
