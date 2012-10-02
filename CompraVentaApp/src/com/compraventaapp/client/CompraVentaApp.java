package com.compraventaapp.client;

import com.compraventaapp.client.model.Persona;
import com.compraventaapp.client.model.Persona.TipoPersona;
import com.compraventaapp.client.service.GetPagosService;
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
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;
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
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
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

		Tab smartTab4 = new Tab("Compras");
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
		Canvas tabPane6 = new Canvas();
		tabPane6.setWidth100();
		tabPane6.setHeight100();
		tabPane6.addChild(getGwtTabPagos());
		smartTab6.setPane(tabPane6);

		Tab smartTab7 = new Tab("Salir");
		tabSet.setTabs(smartTab1, smartTab2, smartTab3, smartTab4, smartTab5,
				smartTab6, smartTab7);
		tabSet.draw();
	}

	private Widget getGwtTabPagos() {
		DecoratedTabPanel tabPanel = new DecoratedTabPanel();
		tabPanel.setWidth("550px");
		tabPanel.setAnimationEnabled(true);

		VerticalPanel vPanel1 = new VerticalPanel();
		vPanel1.setSpacing(15);
		vPanel1.setHeight("500px");
		IntegerItem saldoItem = new IntegerItem();
		saldoItem.setTitle("Saldo");
		tabPanel.add(vPanel1, "Subir Archivo");

		final DynamicForm uploadForm = new DynamicForm();		
		uploadForm.setEncoding(Encoding.MULTIPART);
		UploadItem fileItem = new UploadItem("archivo");
		uploadForm.setAction("archivoPago");
		SubmitItem uploadButton = new SubmitItem();
		uploadButton.setTitle("Subir");
		uploadButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent e){
				uploadForm.submitForm();
			}
		});
			
		uploadForm.setItems(fileItem, uploadButton);
		vPanel1.add(uploadForm);
		
		tabPanel.add(vPanel1, "Subir Archivo");

		VerticalPanel vPanel2 = new VerticalPanel();
		vPanel2.setSpacing(15);
		vPanel2.setHeight("500px");

		final ListGrid listGrid = getPagosGrid();
		vPanel2.add(listGrid);
		tabPanel.add(vPanel2, "Listar Pagos");
		tabPanel.getTabBar().getTab(1).addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GreetingServiceAsync service = (GreetingServiceAsync) GWT
						.create(GreetingService.class);
				ServiceDefTarget serviceDef = (ServiceDefTarget) service;
				serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()
						+ "compraVentaService");
				GetPagosService getPagosCallback = new GetPagosService(
						listGrid);
				try {
					service.getPagos(getPagosCallback);
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
	}

	private ListGrid getPagosGrid() {
		ListGrid pagosGrid = new ListGrid();

		pagosGrid.setShowRecordComponents(true);
		pagosGrid.setShowRecordComponentsByCell(true);

		pagosGrid.setWidth(550);
		pagosGrid.setHeight(224);
		pagosGrid.setShowAllRecords(true);

		ListGridField codPago = new ListGridField("codPago", "Cod. Pago");
		ListGridField codCliente = new ListGridField("codPersona", "Cod. Cliente");
		ListGridField fecha = new ListGridField("fecha", "Fecha");
		fecha.setType(ListGridFieldType.DATE);
		ListGridField monto = new ListGridField("monto", "Monto");
		monto.setType(ListGridFieldType.INTEGER);
		ListGridField estado = new ListGridField("estado", "Estado");
		ListGridField tipoPago = new ListGridField("tipoPago", "Tipo pago");
		
		
		pagosGrid.setFields(codPago, codCliente, fecha, monto, estado, tipoPago);
		pagosGrid.setCanResizeFields(true);

		return pagosGrid;
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
			SubmitItem submitItem = new SubmitItem();
			DynamicForm dynamicForm = new DynamicForm();
			submitItem.setTitle("Guardar");
			dynamicForm.setFields(new FormItem[] {
					new TextItem("newCiruc", "CI/RUC"),
					new TextItem("newNombre", "Nombre"),
					new TextItem("newApellido", "Apellido"),
					new TextItem("newDireccion", "Direcci\u00F3n"),
					new TextItem("newTelefono", "Tel\u00E9fono"), 
					saldoItem,
					submitItem });
			vPanel1.add(dynamicForm);

			VerticalPanel vPanel2 = new VerticalPanel();
			vPanel2.setSpacing(15);
			vPanel2.setHeight("500px");
		

			final ListGrid listGrid = getPersonasGrid(Persona.TipoPersona.CLIENTE);
			vPanel2.add(listGrid);
			tabPanel.add(vPanel2, "Listar Clientes");
			tabPanel.getTabBar().getTab(1).addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					GreetingServiceAsync service = (GreetingServiceAsync) GWT
							.create(GreetingService.class);
					ServiceDefTarget serviceDef = (ServiceDefTarget) service;
					serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL()
							+ "compraVentaService");
					GetPersonasService getPersonasCallback = new GetPersonasService(
							listGrid);
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

		dynamicForm.setFields(new FormItem[] {
				new TextItem("newCiruc", "CI/RUC"),
				new TextItem("newNombre", "Nombre"),
				new TextItem("newApellido", "Apellido"),
				new TextItem("newDireccion", "Direcci\u00F3n"),
				new TextItem("newTelefono", "Tel\u00E9fono"), submitItem });
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

		dynamicForm.setFields(new FormItem[] {
				new TextItem("newNombre", "Nombre"),
				new TextItem("newDescripcion", "Descripci\u00F3n"),
				new TextItem("newPrecioVenta", "Precio Venta"),
				new TextItem("newExistencia", "Existencia"), submitItem });
		vPanel1.add(dynamicForm);

		// Return the content
		tabPanel.selectTab(0);
		tabPanel.ensureDebugId("cwTabPanel");
		return tabPanel;
	}

 /*****************************************************************/
    
    //Comprar
    private Widget getGwtTab4() {
	try{
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
      
        //Listar
        VerticalPanel vPanel2 = new VerticalPanel();  
        vPanel2.setSpacing(15);  
        vPanel2.setHeight("500px");
        final ListGrid listGrid = getComprasGrid(Persona.TipoPersona.PROVEEDOR);
        
        vPanel2.add(listGrid);
        tabPanel.add(vPanel2, "Listar Compra");
        /*
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
		        	}catch (Exception e) {
		        		// TODO Auto-generated catch block
		        		e.printStackTrace();
		        	}
			
        	}
    	   
        });
        */
        	
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
    
    //Listar Compras
    private ListGrid getComprasGrid(TipoPersona proveedor) throws Exception {
    	ListGrid personasGrid = new ListGrid(); 
           
    		
        personasGrid.setShowRecordComponents(true);          
        personasGrid.setShowRecordComponentsByCell(true);  
        personasGrid.setCanRemoveRecords(true);  
  
        personasGrid.setWidth(550);  
        personasGrid.setHeight(224);  
        personasGrid.setShowAllRecords(true);  
  
        ListGridField fechaField = new ListGridField("fecha", "Fecha");  
        ListGridField proveedorField = new ListGridField("proveedor", "Proveedor");  
        ListGridField productoField = new ListGridField("producto", "Producto");
        ListGridField cantidadField = new ListGridField("cantidad", "Cantidad");
        
        personasGrid.setFields(fechaField, proveedorField, productoField, cantidadField);  
        personasGrid.setCanResizeFields(true);  
       
        return personasGrid;
	}
            
    //Vender
    private Widget getGwtTab5() {
    try{
	 	DecoratedTabPanel tabPanel = new DecoratedTabPanel();  
        tabPanel.setWidth("550px");  
        tabPanel.setAnimationEnabled(true);
        
        VerticalPanel vPanel1 = new VerticalPanel();  
        vPanel1.setSpacing(15);  
        vPanel1.setHeight("500px");
       
        tabPanel.add(vPanel1, "Vender");
        final DynamicForm dynamicForm = new DynamicForm();
        SubmitItem submitItem = new SubmitItem();
        submitItem.setTitle("Guardar");
        
        dynamicForm.setFields(new FormItem[] { new TextItem("newFecha", "Fecha"), new TextItem("newCliente", "Cliente"), new TextItem("newProducto", "Producto"), new TextItem("newCantidad", "Cantidad"),submitItem});
        vPanel1.add(dynamicForm);
        
        //Listar
        VerticalPanel vPanel2 = new VerticalPanel();  
        vPanel2.setSpacing(15);  
        vPanel2.setHeight("500px");
        final ListGrid listGrid = getVentasGrid(Persona.TipoPersona.CLIENTE);
        
        vPanel2.add(listGrid);
        tabPanel.add(vPanel2, "Listar Ventas");
        /*
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
		        	}catch (Exception e) {
		        		// TODO Auto-generated catch block
		        		e.printStackTrace();
		        	}
			
        	}
    	   
        });
        */
        	
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

    //Listar Ventas
    private ListGrid getVentasGrid(TipoPersona cliente) throws Exception {
    	ListGrid personasGrid = new ListGrid(); 
           
    		
        personasGrid.setShowRecordComponents(true);          
        personasGrid.setShowRecordComponentsByCell(true);  
        personasGrid.setCanRemoveRecords(true);  
  
        personasGrid.setWidth(550);  
        personasGrid.setHeight(224);  
        personasGrid.setShowAllRecords(true);  
        
        ListGridField fechaField = new ListGridField("fecha", "Fecha");  
        ListGridField clienteField = new ListGridField("cliente", "Cliente");  
        ListGridField productoField = new ListGridField("producto", "Producto");
        ListGridField cantidadField = new ListGridField("cantidad", "Cantidad");
        
        personasGrid.setFields(fechaField, clienteField, productoField, cantidadField);  
        personasGrid.setCanResizeFields(true);  
       
        return personasGrid;
	}
        
    /****************************************************************/
}
