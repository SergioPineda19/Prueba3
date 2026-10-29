/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author segio
 */
public class EmpleadoAdministrativo extends Empleado{
    private double bonificacion;
    private double descuento;
    
    public EmpleadoAdministrativo(String codigo, String nombre, double salarioBase, double bonificacion, double descuento){
        super(codigo, nombre,salarioBase);
        setBonificacion(bonificacion);
        setDescuento(descuento);
        
    }
    public double getBonificacion(){
        return bonificacion;
    }
    
    public void setBonificacion(double bonificacion) {
        if (Double.isNaN(bonificacion) || Double.isInfinite(bonificacion)
                || bonificacion < 0) {
            throw new IllegalArgumentException("Bonificacion invalida");
        }
        this.bonificacion = bonificacion;
    }
    
    public double getDescuento(){
        return descuento;
    }
    
   public void setDescuento(double descuento) {
        if (Double.isNaN(descuento) || Double.isInfinite(descuento)
                || descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("Descuento invalido");
        }
        this.descuento = descuento;
    }
   
    @Override
   public double calcularSalario(){
       return (getSalarioBase()+bonificacion)*(1-descuento/100);
   }
}
