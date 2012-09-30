package com.compraventaapp.client;

import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.Persona.TipoPersona;
import com.compraventaapp.client.service.GetPersonasService;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Entry point classes define <code>onModuleLoad()</code>. hola
 */
public class CompraVentaApp implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TabSet tabSet = new TabSet();  
        tabSet.setWidth(Window.getClientWidth());  
        tabSet.setHeight(Window.getClientHeight());  
  
        Tab smartTab1 = new Tab("Clientes");  
        Canvas tabPane1 = new Canvas();  
        tabPane1.setWidth100();
        tabPane1.setHeight100();  
        tabPane1.addChild(getGwtTab());  
        smartTab1.setPane(tabPane1); 

        Tab smartTab2 = new Tab("Proveedores");
        Canvas tabPane2 = new Canvas();  
        tabPane2.setWidth100();
        tabPane2.setHeight100();  
        tabPane2.addChild(getGwtTab2());  
        smartTab2.setPane(tabPane2); 
        
        Tab smartTab3 = new Tab("Productos"); 
        Canvas tabPane3 = new Canvas();  
        tabPane3.setWidth100();
        tabPane3.setHeight100();  
        tabPane3.addChild(getGwtTab3());  
        smartTab3.setPane(tabPane3); 
        
        Tab smartTab4= new Tab("Compras"); 
        Canvas tabPane4 = new Canvas();  
        tabPane4.setWidth100();
        tabPane4.setHeight100();  
        tabPane4.addChild(getGwtTab4());  
        smartTab4.setPane(tabPane4); 
       
        Tab smartTab5 = new Tab("Ventas"); 
        Canvas tabPane5 = new Canvas();  
        tabPane5.setWidth100();
        tabPane5.setHeight100();  
        tabPane5.addChild(getGwtTab5());  
        smartTab5.setPane(tabPane5); 
        
        Tab smartTab6 = new Tab("Pagos");
        Tab smartTab7 = new Tab("Salir");
        tabSet.setTabs(smartTab1, smartTab2, smartTab3, smartTab4, smartTab5, smartTab6, smartTab7);  
        tabSet.draw();  
    }  
  
    private Widget getGwtTab() {  
    	try {
    	DecoratedTabPanel tabPanel = new DecoratedTabPanel();  
        tabPanel.setWidth("550px");  
        tabPanel.setAnimationEnabled(true);
        
        VerticalPanel vPanel1 = new VerticalPanel();  
        vPanel1.setSpacing(15);  
        vPanel1.setHeight("500px");
        IntegerItem saldoItem = new IntegerItem();
        saldoItem.setTitle("Saldo");
        tabPanel.add(vPanel1, "Nuevo Cliente");
        DynamicForm dynamicForm = new DynamicForm();
        SubmitItem submitItem = new SubmitItem();
        submitItem.setTitle("Guardar");
        dynamicForm.setFields(new FormItem[] { new TextItem("newCiruc", "CI/RUC"), new TextItem("newNombre", "Nombre"), new TextItem("newApellido", "Apellido"), new TextItem("newDireccion", "Direcci\u00F3n"), new TextItem("newTelefono", "Tel\u00E9fono"), saldoItem, submitItem});
        vPanel1.add(dynamicForm);

        VerticalPanel vPanel2 = new VerticalPanel();  
        vPanel2.setSpacing(15);  
        vPanel2.setHeight("500px");
        
       final ListGrid listGrid = getPersonasGrid(Persona.TipoPersona.CLIENTE);
//       vPanel2.addHandler(new ClickHandler(){
//
//		@Override
//		public void onClick(ClickEvent event) {
//			 GreetingServiceAsync service = (GreetingServiceAsync) GWT
//		                .create(GreetingService.class);
//		        ServiceDefTarget serviceDef = (ServiceDefTarget) service;
//		        serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()
//		            + "compraVentaService");
//		        GetPersonasService getPersonasCallback = new GetPersonasService(listGrid);
//		        try {
//					service.getClientes(getPersonasCallback);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//		}
//    	   
//       }, ClickEvent.getType());
       
		
        vPanel2.add(listGrid);
        tabPanel.add(vPanel2, "Listar Clientes");
        tabPanel.getTabBar().getTab(1).addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			 GreetingServiceAsync service = (GreetingServiceAsync) GWT
		                .create(GreetingService.class);
		        ServiceDefTarget serviceDef = (ServiceDefTarget) service;
		        serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()
		            + "compraVentaService");
		        GetPersonasService getPersonasCallback = new GetPersonasService(listGrid);
		        try {
					service.getClientes(getPersonasCallback);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
    	   
       });
  
        // Return the content  
        tabPanel.selectTab(0);  
        tabPanel.ensureDebugId("cwTabPanel");  
        return tabPanel;  
    	 } catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	return new DecoratedTabPanel();
    }  
    private ListGrid getPersonasGrid(TipoPersona cliente) throws Exception {
    	ListGrid personasGrid = new ListGrid(); 
           
    		
        personasGrid.setShowRecordComponents(true);          
        personasGrid.setShowRecordComponentsByCell(true);  
        personasGrid.setCanRemoveRecords(true);  
  
        personasGrid.setWidth(550);  
        personasGrid.setHeight(224);  
        personasGrid.setShowAllRecords(true);  
  
        
        ListGridField nameField = new ListGridField("nombre", "Nombre");  
        ListGridField capitalField = new ListGridField("apellido", "Apelllido");  
  
        personasGrid.setFields(nameField, capitalField);  
        personasGrid.setCanResizeFields(true);  
       
        return personasGrid;
	}

	private Widget getGwtTab2() {  
    	 	DecoratedTabPanel tabPanel = new DecoratedTabPanel();  
	        tabPanel.setWidth("550px");  
	        tabPanel.setAnimationEnabled(true);
	        
	        VerticalPanel vPanel1 = new VerticalPanel();  
	        vPanel1.setSpacing(15);  
	        vPanel1.setHeight("500px");
	       
	        tabPanel.add(vPanel1, "Nuevo Proveedor");
	        final DynamicForm dynamicForm = new DynamicForm();
	        SubmitItem submitItem = new SubmitItem();
	        submitItem.setTitle("Guardar");
	        
	        dynamicForm.setFields(new FormItem[] { new TextItem("newCiruc", "CI/RUC"), new TextItem("newNombre", "Nombre"), new TextItem("newApellido", "Apellido"), new TextItem("newDireccion", "Direcci\u00F3n"), submitItem, new TextItem("newTelefono", "Tel\u00E9fono")});
	        vPanel1.add(dynamicForm);

	        VerticalPanel vPanel2 = new VerticalPanel();  
	        vPanel2.setSpacing(15);  
	        vPanel2.setHeight("500px");

	        tabPanel.add(vPanel2, "Listar Proveedores");
	  
	        // Return the content  
	        tabPanel.selectTab(0);  
	        tabPanel.ensureDebugId("cwTabPanel");  
	        return tabPanel;    
    }
    private Widget getGwtTab3() {  
	 	DecoratedTabPanel tabPanel = new DecoratedTabPanel();  
        tabPanel.setWidth("550px");  
        tabPanel.setAnimationEnabled(true);
        
        VerticalPanel vPanel1 = new VerticalPanel();  
        vPanel1.setSpacing(15);  
        vPanel1.setHeight("500px");
       
        tabPanel.add(vPanel1, "Comprar");
        final DynamicForm dynamicForm = new DynamicForm();
        SubmitItem submitItem = new SubmitItem();
        submitItem.setTitle("Guardar");
    
        dynamicForm.setFields(new FormItem[] { new TextItem("newNombre", "Nombre"), new TextItem("newDescripcion", "Descripci\u00F3n"), new TextItem("newPrecioVenta", "Precio Venta"), new TextItem("newExistencia", "Existencia"),submitItem});
        vPanel1.add(dynamicForm);

  
        // Return the content  
        tabPanel.selectTab(0);  
        tabPanel.ensureDebugId("cwTabPanel");  
        return tabPanel;    
    }  
    private Widget getGwtTab4() {  
	 	DecoratedTabPanel tabPanel = new DecoratedTabPanel();  
        tabPanel.setWidth("550px");  
        tabPanel.setAnimationEnabled(true);
        
        VerticalPanel vPanel1 = new VerticalPanel();  
        vPanel1.setSpacing(15);  
        vPanel1.setHeight("500px");
       
        tabPanel.add(vPanel1, "Comprar");
        final DynamicForm dynamicForm = new DynamicForm();
        SubmitItem submitItem = new SubmitItem();
        submitItem.setTitle("Guardar");
    
        dynamicForm.setFields(new FormItem[] { new TextItem("newFecha", "Fecha"), new TextItem("newProveedor", "Proveedor"), new TextItem("newProducto", "Producto"), new TextItem("newCantidad", "Cantidad"),submitItem});
        vPanel1.add(dynamicForm);

        // Return the content  
        tabPanel.selectTab(0);  
        tabPanel.ensureDebugId("cwTabPanel");  
        return tabPanel;    
}  
    private Widget getGwtTab5() {  
	 	DecoratedTabPanel tabPanel = new DecoratedTabPanel();  
        tabPanel.setWidth("550px");  
        tabPanel.setAnimationEnabled(true);
        
        VerticalPanel vPanel1 = new VerticalPanel();  
        vPanel1.setSpacing(15);  
        vPanel1.setHeight("500px");
       
        tabPanel.add(vPanel1, "Comprar");
        final DynamicForm dynamicForm = new DynamicForm();
        SubmitItem submitItem = new SubmitItem();
        submitItem.setTitle("Guardar");
    
        dynamicForm.setFields(new FormItem[] { new TextItem("newFecha", "Fecha"), new TextItem("newCliente", "Cliente"), new TextItem("newProducto", "Producto"), new TextItem("newCantidad", "Cantidad"),submitItem});
        vPanel1.add(dynamicForm);

        // Return the content  
        tabPanel.selectTab(0);  
        tabPanel.ensureDebugId("cwTabPanel");  
        return tabPanel;    
}  
}
