package modelo;

public class Medidor 
{
    

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Medidor Attributes
  private String marcaMedidor;
  private double costoMedidor;
  private String direccionInstalacion;

  //Medidor Associations
  private Cliente cliente;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Medidor(String aMarcaMedidor, double aCostoMedidor, String aDireccionInstalacion, Cliente aCliente)
  {
    marcaMedidor = aMarcaMedidor;
    costoMedidor = aCostoMedidor;
    direccionInstalacion = aDireccionInstalacion;
    boolean didAddCliente = setCliente(aCliente);
    if (!didAddCliente)
    {
      throw new RuntimeException("Unable to create medidor due to cliente. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMarcaMedidor(String aMarcaMedidor)
  {
    boolean wasSet = false;
    marcaMedidor = aMarcaMedidor;
    wasSet = true;
    return wasSet;
  }

  public boolean setCostoMedidor(double aCostoMedidor)
  {
    boolean wasSet = false;
    costoMedidor = aCostoMedidor;
    wasSet = true;
    return wasSet;
  }

  public boolean setDireccionInstalacion(String aDireccionInstalacion)
  {
    boolean wasSet = false;
    direccionInstalacion = aDireccionInstalacion;
    wasSet = true;
    return wasSet;
  }

  public String getMarcaMedidor()
  {
    return marcaMedidor;
  }

  public double getCostoMedidor()
  {
    return costoMedidor;
  }

  public String getDireccionInstalacion()
  {
    return direccionInstalacion;
  }
  /* Code from template association_GetOne */
  public Cliente getCliente()
  {
    return cliente;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCliente(Cliente aCliente)
  {
    boolean wasSet = false;
    if (aCliente == null)
    {
      return wasSet;
    }

    Cliente existingCliente = cliente;
    cliente = aCliente;
    if (existingCliente != null && !existingCliente.equals(aCliente))
    {
      existingCliente.removeMedidor(this);
    }
    cliente.addMedidor(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Cliente placeholderCliente = cliente;
    this.cliente = null;
    if(placeholderCliente != null)
    {
      placeholderCliente.removeMedidor(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "marcaMedidor" + ":" + getMarcaMedidor()+ "," +
            "costoMedidor" + ":" + getCostoMedidor()+ "," +
            "direccionInstalacion" + ":" + getDireccionInstalacion()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "cliente = "+(getCliente()!=null?Integer.toHexString(System.identityHashCode(getCliente())):"null");
  }
}