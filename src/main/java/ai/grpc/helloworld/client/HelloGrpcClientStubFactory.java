package ai.grpc.helloworld.client;

import java.util.concurrent.TimeUnit;

import al.grpc.helloworld.HelloGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloGrpcClientStubFactory {

	private final ManagedChannel channel;
	@Getter
	private final HelloGrpc.HelloBlockingStub blockingStub;
	@Getter
	private final HelloGrpc.HelloStub asyncStub;
	@Getter
	private final HelloGrpc.HelloFutureStub futureStub;

	public HelloGrpcClientStubFactory(String host, int port) {
		this.channel = ManagedChannelBuilder.forAddress(host, port)
			.usePlaintext(true)
			.build();
		this.blockingStub = HelloGrpc.newBlockingStub(channel);
		this.asyncStub = HelloGrpc.newStub(channel);
		this.futureStub = HelloGrpc.newFutureStub(channel);
	}

	public void shutdownChannel() throws InterruptedException {
		log.info("gRPC Channel shutdown...");
		this.channel.shutdown().awaitTermination(2, TimeUnit.SECONDS);
	}

}
