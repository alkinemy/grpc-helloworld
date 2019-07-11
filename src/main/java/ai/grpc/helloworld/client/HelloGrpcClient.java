package ai.grpc.helloworld.client;

import al.grpc.helloworld.HelloGrpc;
import al.grpc.helloworld.HelloRequest;
import al.grpc.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloGrpcClient {

	private final HelloGrpc.HelloBlockingStub blockingStub;
	private final HelloGrpc.HelloStub asyncStub;
	private final HelloGrpc.HelloFutureStub futureStub;

	public HelloGrpcClient(HelloGrpc.HelloBlockingStub blockingStub, HelloGrpc.HelloStub asyncStub, HelloGrpc.HelloFutureStub futureStub) {
		this.blockingStub = blockingStub;
		this.asyncStub = asyncStub;
		this.futureStub = futureStub;
	}

	public void sendAsyncUnaryMessage() {
		//클라리언트 비즈니스 로직 수행 결과를 message에 할당인 clientName으로 request 생성
		String message = "입금해 형ㅋ";

		//message로 request 생성
		HelloRequest request = HelloRequest.newBuilder().setRequest(message).build();

		//서버에서 보낼 데이터를 담은 request와
		//비동기 방식으로 서버에서 호출될 콜백 객체도 함께 파라미터로 전달
		log.info("[김정은] 요청 전송: {}", message);

		//두번째 인자: 서버에 보낼 콜백 객체
		asyncStub.unaryHello(request, new StreamObserver<>() {
			@Override
			public void onNext(HelloResponse response) {
				log.info("[트럼프로부터의 응답]: {}", response.getResponse());
			}

			@Override
			public void onError(Throwable t) {
				log.error("Async Unary responseObserver.onError() 호출됨");
			}

			@Override
			public void onCompleted() {
				log.info("[트럼프로부터의 응답 종료]");
			}
		});

		//서버에서 응답이 올 때 까지 기다리지 않고, 호출 결과에 상관없이 다른 작업 수행 가능
		log.info("[김정은]: 걍 핵이나 쏴야지 ㅋㅋ");
	}

}
