import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.DeploymentEvent;
import io.zeebe.client.api.response.WorkflowInstanceEvent;

public class app {

    public static void main(String[] args) {

        final ZeebeClient client = ZeebeClient.newClientBuilder()
                // change the contact point if needed
                .brokerContactPoint("127.0.0.1:26500")
                .usePlaintext()
                .build();

        System.out.println("Connected.");

        // after the client is connected

//        final DeploymentEvent deployment = client.newDeployCommand()
//                .addResourceFromClasspath("order-process.bpmn")
//                .send()
//                .join();
//
//        final int version = deployment.getWorkflows().get(0).getVersion();
//        System.out.println("Workflow deployed. Version: " + version);

        // after the workflow is deployed

        final WorkflowInstanceEvent wfInstance = client.newCreateInstanceCommand()
                .bpmnProcessId("Process_0dphuhp")
                .latestVersion()
                .send()
                .join();

        final long workflowInstanceKey = wfInstance.getWorkflowInstanceKey();

        System.out.println("Workflow instance created. Key: " + workflowInstanceKey);

        client.close();
        System.out.println("Closed.");

    }
}
