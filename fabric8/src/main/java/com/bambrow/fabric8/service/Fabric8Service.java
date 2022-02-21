package com.bambrow.fabric8.service;

import com.bambrow.fabric8.config.Fabric8Config;
import com.bambrow.fabric8.util.Fabric8Utils;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.events.v1.Event;
import io.fabric8.kubernetes.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Fabric8Service {

    @Autowired
    private Fabric8Config config;

    private KubernetesClient client;
    private final Logger log = LoggerFactory.getLogger(Fabric8Service.class);

    @PostConstruct
    private void init() {
        Config k8sConfig = new ConfigBuilder().withMasterUrl(config.getMasterUrl()).build();
        client = new DefaultKubernetesClient(k8sConfig);
    }

    public List<String> listNamespaces() {
        return client.namespaces().list().getItems().stream().map(x -> x.getMetadata().getName()).collect(Collectors.toList());
    }

    public List<String> listNodes() {
        return client.nodes().list().getItems().stream().map(x -> x.getMetadata().getName()).collect(Collectors.toList());
    }

    public List<String> listObj(String namespace, String kind) {
        KubernetesResourceList<? extends HasMetadata> obj = client.resources(Fabric8Utils.getClassForKind(kind)).inNamespace(namespace).list();
        return obj.getItems().stream().map(x -> x.getMetadata().getName()).collect(Collectors.toList());
    }

    public Namespace editNamespace(String namespace) {
        return client.namespaces().withName(namespace).edit(x -> new NamespaceBuilder(x)
                .editMetadata()
                .addToLabels("create-by", "fabric8")
                .endMetadata()
                .build());
    }

    public Namespace createNamespace(String namespace) {
        return client.namespaces().create(new NamespaceBuilder()
                .withNewMetadata()
                .withName(namespace)
                .addToLabels("create-by", "fabric8")
                .endMetadata()
                .build());
    }

    public Watch createWatcher() {
        return client.events().v1().events().inAnyNamespace().watch(new Watcher<Event>() {
            @Override
            public void eventReceived(Action action, Event resource) {
                log.info("Event: {}, {}", action.name(), resource.toString());
            }

            @Override
            public void onClose(WatcherException cause) {
                log.info("Watcher close due to: {}", cause.toString());
            }
        });
    }

}
