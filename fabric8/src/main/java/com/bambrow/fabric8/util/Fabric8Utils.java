package com.bambrow.fabric8.util;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;

public class Fabric8Utils {

    public static Class<? extends HasMetadata> getClassForKind(String kind) {
        switch (kind) {
            case "pod":
            case "pods":
                return Pod.class;
            case "deploy":
            case "deployment":
            case "deployments":
                return Deployment.class;
            case "sts":
            case "statefulset":
            case "statefulsets":
                return StatefulSet.class;
            case "svc":
            case "service":
            case "services":
                return Service.class;
            case "node":
            case "nodes":
                return Node.class;
            case "ns":
            case "namespace":
            case "namespaces":
                return Namespace.class;
            case "rc":
            case "replicationcontroller":
            case "replicationcontrollers":
                return ReplicationController.class;
        }
        return null;
    }

}
