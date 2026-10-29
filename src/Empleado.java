/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author segio
 */
public abstract class Empleado implements Cloneable{
    private String codigo;
    private String nombre;
    private double salarioBase;
    
    public Empleado(String codigo, String nombre, double salarioBase){
        setCodigo(codigo);
        setNombre(nombre);
        setSalarioBase(salarioBase);
        
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setCodigo(String codigo){
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
        this.codigo=codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }
    
    public double getSalarioBase(){
        return salarioBase;
    }
    
   public void setSalarioBase(double salarioBase) {
        if (Double.isNaN(salarioBase) || Double.isInfinite(salarioBase)
                || salarioBase < 0) {
            throw new IllegalArgumentException("Salario base invalido");
        }
        this.salarioBase = salarioBase;
    }
   
   public abstract double calcularSalario();
   
   public String mostrarInformacion(){
       return "Codigo: "+codigo+" Nombre: "+nombre+" Salario: "+calcularSalario();
   }
   
   public String obtenerCategoria(){
       double salario=calcularSalario();
       if (salario>=20000) {
           return "Superior";
       }else if(salario>=10000){
           return "Estandar";
       }else{
           return "Basica";
       }
   }
   
    @Override
   public Empleado clone(){
       try{
           return(Empleado)super.clone();
       }catch(CloneNotSupportedException e){
           throw new IllegalStateException("Error al clonar el emppleado",e);
       }
   }

    
}
