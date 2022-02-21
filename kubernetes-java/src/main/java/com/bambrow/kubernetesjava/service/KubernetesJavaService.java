package com.bambrow.kubernetesjava.service;

import com.bambrow.kubernetesjava.config.KubernetesJavaConfig;
import com.bambrow.kubernetesjava.util.KubernetesJavaUtils;
import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.extended.kubectl.Kubectl;
import io.kubernetes.client.extended.kubectl.exception.KubectlException;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class KubernetesJavaService {

    @Autowired
    private KubernetesJavaConfig config;

    @PostConstruct
    private void init() {
        ModelMapper.addModelMap("", "v1", "Node", "nodes", false, V1Node.class);
        ModelMapper.addModelMap("", "v1", "Namespace", "namespaces", false, V1Namespace.class);
        ModelMapper.addModelMap("", "v1", "Pod", "pods", true, V1Pod.class);
        ModelMapper.addModelMap("apps", "v1", "Deployment", "deployments", true, V1Deployment.class);
        ModelMapper.addModelMap("apps", "v1", "StatefulSet", "statefulsets", true, V1StatefulSet.class);
        ModelMapper.addModelMap("", "v1", "Service", "services", true, V1Service.class);
        ModelMapper.addModelMap("", "v1", "ReplicationController", "replicationcontrollers", true, V1ReplicationController.class);
        ApiClient client = new ClientBuilder().setBasePath(config.getMasterUrl()).build();
        Configuration.setDefaultApiClient(client);
    }

    public List<String> listNamespaces() throws KubectlException {
        List<V1Namespace> ns = Kubectl.get(V1Namespace.class).execute();
        return ns.stream().map(x -> Objects.requireNonNull(x.getMetadata()).getName())
                .collect(Collectors.toList());
    }

    public List<String> listNodes() throws KubectlException {
        List<V1Node> nodes = Kubectl.get(V1Node.class).execute();
        return nodes.stream().map(x -> Objects.requireNonNull(x.getMetadata()).getName())
                .collect(Collectors.toList());
    }

    public List<String> listObj(String namespace, String kind) throws KubectlException {
        List<? extends KubernetesObject> obj = Kubectl.get(KubernetesJavaUtils.getClassForKind(kind)).namespace(namespace).execute();
        return obj.stream().map(x -> Objects.requireNonNull(x.getMetadata()).getName())
                .collect(Collectors.toList());
    }

    public Map<String, String> labelObj(String namespace, String kind, String name) throws KubectlException {
        KubernetesObject obj = Kubectl.label(KubernetesJavaUtils.getClassForKind(kind)).namespace(namespace).name(name).addLabel("labelled-by", "kubernetes-java-client").execute();
        return obj.getMetadata().getLabels();
    }

}
