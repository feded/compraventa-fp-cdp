/**
 * CargaPagosServiceSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package remote.webService.generated;

public class CargaPagosServiceSoapBindingSkeleton implements session.beans.interfaces.CargaPagosWebService, org.apache.axis.wsdl.Skeleton {
    private session.beans.interfaces.CargaPagosWebService impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("cargarPagos", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://interfaces.beans.session/", "cargarPagos"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("cargarPagos") == null) {
            _myOperations.put("cargarPagos", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("cargarPagos")).add(_oper);
    }

    public CargaPagosServiceSoapBindingSkeleton() {
        this.impl = new remote.webService.generated.CargaPagosServiceSoapBindingImpl();
    }

    public CargaPagosServiceSoapBindingSkeleton(session.beans.interfaces.CargaPagosWebService impl) {
        this.impl = impl;
    }
    public void cargarPagos(java.lang.String arg0) throws java.rmi.RemoteException
    {
        impl.cargarPagos(arg0);
    }

}
