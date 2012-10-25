/**
 * CargaPagosServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package remote.webService.generated;

public class CargaPagosServiceLocator extends org.apache.axis.client.Service implements remote.webService.generated.CargaPagosService {

    public CargaPagosServiceLocator() {
    }


    public CargaPagosServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CargaPagosServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CargaPagosPort
    private java.lang.String CargaPagosPort_address = "http://Fede-PC:8080/test1/CargaPagos";

    public java.lang.String getCargaPagosPortAddress() {
        return CargaPagosPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CargaPagosPortWSDDServiceName = "CargaPagosPort";

    public java.lang.String getCargaPagosPortWSDDServiceName() {
        return CargaPagosPortWSDDServiceName;
    }

    public void setCargaPagosPortWSDDServiceName(java.lang.String name) {
        CargaPagosPortWSDDServiceName = name;
    }

    public session.beans.interfaces.CargaPagosWebService getCargaPagosPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CargaPagosPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCargaPagosPort(endpoint);
    }

    public session.beans.interfaces.CargaPagosWebService getCargaPagosPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            remote.webService.generated.CargaPagosServiceSoapBindingStub _stub = new remote.webService.generated.CargaPagosServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCargaPagosPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCargaPagosPortEndpointAddress(java.lang.String address) {
        CargaPagosPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (session.beans.interfaces.CargaPagosWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                remote.webService.generated.CargaPagosServiceSoapBindingStub _stub = new remote.webService.generated.CargaPagosServiceSoapBindingStub(new java.net.URL(CargaPagosPort_address), this);
                _stub.setPortName(getCargaPagosPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CargaPagosPort".equals(inputPortName)) {
            return getCargaPagosPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://beans.session/", "CargaPagosService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://beans.session/", "CargaPagosPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CargaPagosPort".equals(portName)) {
            setCargaPagosPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
