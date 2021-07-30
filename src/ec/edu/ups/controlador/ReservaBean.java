package ec.edu.ups.controlador;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Reserva;
import ec.edu.ups.entidades.Restaurante;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ReservaBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@EJB
	private ReservaFacade ejbReserva;
	@EJB
	private ClienteFacade ejbCliente;
	@EJB 
	private RestauranteFacade ejbRestaurante;
	
	private String nombre;
	private String apellido;
    private String direccion;
    private String telefono;
    private String cedula;
    private String correo;
    private Cliente cliente; 
    
    
    private String nombreRestaurante;
    private Restaurante restaurante;
    private String direccionRestuarante;
	private String telefonoRestuarante;
	private int aforo;
    
	
	
	private String fechaIngreso; 
	private int numeroPersonas; 
	private String mensaje;
   
	public ReservaBean() {
		
	}
	



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}

	public String getDireccionRestuarante() {
		return direccionRestuarante;
	}

	public void setDireccionRestuarante(String direccionRestuarante) {
		this.direccionRestuarante = direccionRestuarante;
	}

	public String getTelefonoRestuarante() {
		return telefonoRestuarante;
	}

	public void setTelefonoRestuarante(String telefonoRestuarante) {
		this.telefonoRestuarante = telefonoRestuarante;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	 
	 
	
}
