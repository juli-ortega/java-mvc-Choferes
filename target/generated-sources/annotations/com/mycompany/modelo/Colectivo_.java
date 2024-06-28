package com.mycompany.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Socio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T12:41:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Colectivo.class)
public class Colectivo_ { 

    public static volatile SingularAttribute<Colectivo, Integer> km;
    public static volatile SingularAttribute<Colectivo, Socio> chofer;
    public static volatile SingularAttribute<Colectivo, Integer> cantidad_pasajeros;
    public static volatile SingularAttribute<Colectivo, Integer> id;
    public static volatile SingularAttribute<Colectivo, String> modelo;
    public static volatile SingularAttribute<Colectivo, String> patente;

}