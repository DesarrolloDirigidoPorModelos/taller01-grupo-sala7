package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente 
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------
  
    //Cliente Attributes
    private String nombres;
    private String apellidos;
    private int dui;
    private String direccion;
  
    //Cliente Associations
    private Municipio municipio;
    private List<Medidor> medidors;
  
    //------------------------
    // CONSTRUCTOR
    //------------------------
  
    public Cliente(String aNombres, String aApellidos, int aDui, String aDireccion, Municipio aMunicipio)
    {
      nombres = aNombres;
      apellidos = aApellidos;
      dui = aDui;
      direccion = aDireccion;
      boolean didAddMunicipio = setMunicipio(aMunicipio);
      if (!didAddMunicipio)
      {
        throw new RuntimeException("Unable to create cliente due to municipio. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
      }
      medidors = new ArrayList<Medidor>();
    }
  
    //------------------------
    // INTERFACE
    //------------------------
  
    public boolean setNombres(String aNombres)
    {
      boolean wasSet = false;
      nombres = aNombres;
      wasSet = true;
      return wasSet;
    }
  
    public boolean setApellidos(String aApellidos)
    {
      boolean wasSet = false;
      apellidos = aApellidos;
      wasSet = true;
      return wasSet;
    }
  
    public boolean setDui(int aDui)
    {
      boolean wasSet = false;
      dui = aDui;
      wasSet = true;
      return wasSet;
    }
  
    public boolean setDireccion(String aDireccion)
    {
      boolean wasSet = false;
      direccion = aDireccion;
      wasSet = true;
      return wasSet;
    }
  
    public String getNombres()
    {
      return nombres;
    }
  
    public String getApellidos()
    {
      return apellidos;
    }
  
    public int getDui()
    {
      return dui;
    }
  
    public String getDireccion()
    {
      return direccion;
    }
    /* Code from template association_GetOne */
    public Municipio getMunicipio()
    {
      return municipio;
    }
    /* Code from template association_GetMany */
    public Medidor getMedidor(int index)
    {
      Medidor aMedidor = medidors.get(index);
      return aMedidor;
    }
  
    public List<Medidor> getMedidors()
    {
      List<Medidor> newMedidors = Collections.unmodifiableList(medidors);
      return newMedidors;
    }
  
    public int numberOfMedidors()
    {
      int number = medidors.size();
      return number;
    }
  
    public boolean hasMedidors()
    {
      boolean has = medidors.size() > 0;
      return has;
    }
  
    public int indexOfMedidor(Medidor aMedidor)
    {
      int index = medidors.indexOf(aMedidor);
      return index;
    }
    /* Code from template association_SetOneToMany */
    public boolean setMunicipio(Municipio aMunicipio)
    {
      boolean wasSet = false;
      if (aMunicipio == null)
      {
        return wasSet;
      }
  
      Municipio existingMunicipio = municipio;
      municipio = aMunicipio;
      if (existingMunicipio != null && !existingMunicipio.equals(aMunicipio))
      {
        existingMunicipio.removeCliente(this);
      }
      municipio.addCliente(this);
      wasSet = true;
      return wasSet;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfMedidors()
    {
      return 0;
    }
    /* Code from template association_AddManyToOne */
    public Medidor addMedidor(String aMarcaMedidor, double aCostoMedidor, String aDireccionInstalacion)
    {
      return new Medidor(aMarcaMedidor, aCostoMedidor, aDireccionInstalacion, this);
    }
  
    public boolean addMedidor(Medidor aMedidor)
    {
      boolean wasAdded = false;
      if (medidors.contains(aMedidor)) { return false; }
      Cliente existingCliente = aMedidor.getCliente();
      boolean isNewCliente = existingCliente != null && !this.equals(existingCliente);
      if (isNewCliente)
      {
        aMedidor.setCliente(this);
      }
      else
      {
        medidors.add(aMedidor);
      }
      wasAdded = true;
      return wasAdded;
    }
  
    public boolean removeMedidor(Medidor aMedidor)
    {
      boolean wasRemoved = false;
      //Unable to remove aMedidor, as it must always have a cliente
      if (!this.equals(aMedidor.getCliente()))
      {
        medidors.remove(aMedidor);
        wasRemoved = true;
      }
      return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addMedidorAt(Medidor aMedidor, int index)
    {  
      boolean wasAdded = false;
      if(addMedidor(aMedidor))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfMedidors()) { index = numberOfMedidors() - 1; }
        medidors.remove(aMedidor);
        medidors.add(index, aMedidor);
        wasAdded = true;
      }
      return wasAdded;
    }
  
    public boolean addOrMoveMedidorAt(Medidor aMedidor, int index)
    {
      boolean wasAdded = false;
      if(medidors.contains(aMedidor))
      {
        if(index < 0 ) { index = 0; }
        if(index > numberOfMedidors()) { index = numberOfMedidors() - 1; }
        medidors.remove(aMedidor);
        medidors.add(index, aMedidor);
        wasAdded = true;
      } 
      else 
      {
        wasAdded = addMedidorAt(aMedidor, index);
      }
      return wasAdded;
    }
  
    public void delete()
    {
      Municipio placeholderMunicipio = municipio;
      this.municipio = null;
      if(placeholderMunicipio != null)
      {
        placeholderMunicipio.removeCliente(this);
      }
      for(int i=medidors.size(); i > 0; i--)
      {
        Medidor aMedidor = medidors.get(i - 1);
        aMedidor.delete();
      }
    }
  
  
    public String toString()
    {
      return super.toString() + "["+
              "nombres" + ":" + getNombres()+ "," +
              "apellidos" + ":" + getApellidos()+ "," +
              "dui" + ":" + getDui()+ "," +
              "direccion" + ":" + getDireccion()+ "]" + System.getProperties().getProperty("line.separator") +
              "  " + "municipio = "+(getMunicipio()!=null?Integer.toHexString(System.identityHashCode(getMunicipio())):"null");
    }

    public void comprarMedidor(Medidor medidor){
        //medidors.add(medidor);
        System.out.println("medidor comprado");

        System.out.println("Medidores del cliente: "+medidors.toString());

    }
  }