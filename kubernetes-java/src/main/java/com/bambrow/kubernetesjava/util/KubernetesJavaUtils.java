package com.bambrow.kubernetesjava.util;

import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.openapi.models.*;

public class KubernetesJavaUtils {

    public static Class<? extends KubernetesObject> getClassForKind(String kind) {
        switch (kind) {
            case "pod":
            case "pods":
                return V1Pod.class;
            case "deploy":
            case "deployment":
            case "deployments":
                return V1Deployment.class;
            case "sts":
            case "statefulset":
            case "statefulsets":
                return V1StatefulSet.class;
            case "svc":
            case "service":
            case "services":
                return V1Service.class;
            case "node":
            case "nodes":
                return V1Node.class;
            case "ns":
            case "namespace":
            case "namespaces":
                return V1Namespace.class;
            case "rc":
            case "replicationcontroller":
            case "replicationcontrollers":
                return V1ReplicationController.class;
        }
        return null;
    }

}
