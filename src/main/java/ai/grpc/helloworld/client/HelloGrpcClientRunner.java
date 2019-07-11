package ai.grpc.helloworld.client;

public class HelloGrpcClientRunner {

	public static void main(String[] args) throws InterruptedException {
		String host = "localhost";
		int port = 54321;

		HelloGrpcClientStubFactory factory = new HelloGrpcClientStubFactory(host, port);
		HelloGrpcClient client = new HelloGrpcClient(factory.getBlockingStub(), factory.getAsyncStub(), factory.getFutureStub());

		client.sendAsyncUnaryMessage();
		Thread.sleep(3000);
		factory.shutdownChannel(); //예제라서 닫은 것임
	}
}
