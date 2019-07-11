package ai.grpc.helloworld.server;

import al.grpc.helloworld.HelloGrpc;
import al.grpc.helloworld.HelloRequest;
import al.grpc.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloGrpcServerService extends HelloGrpc.HelloImplBase {

	@Override
	public void unaryHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
		log.info("[트럼프] 요청 받음: {}", request.getRequest());

		//1초 동안 비즈니스 로직 처리 후에 응답한다고 가정
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("Business task interrupted", e);
		}

		HelloResponse response = HelloResponse.newBuilder()
			.setResponse("입금했다 미친XX야!!")
			.build();

		//응답 시작
		responseObserver.onNext(response);
		//응답 시작 1초 후에 응답 완료된다고 가정
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("Response waiting interrupted", e);
		}
		responseObserver.onCompleted();
	}
}
