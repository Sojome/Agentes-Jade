/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Policia;

import jade.content.onto.*;
import jade.content.schema.*;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;

/**
 *
 * @author Aaron Jaramillo
 */

//-gui -port 1098 comisaria:ontologias.Policia.Comisaria;testigo:ontologias.Policia.Testigo(comisaria,testigo)
public class PoliciaOntology extends jade.content.onto.Ontology {

    //NAME

    public static final String ONTOLOGY_NAME = "policia";
    // The singleton instance of this ontology
    private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
    private static Ontology theInstance = new PoliciaOntology();

    public static Ontology getInstance() {
        return theInstance;
    }

    // VOCABULARY
    public static final String DISPONIBLE_TIEMPO = "TIEMPO";
    public static final String DISPONIBLE = "Disponible";
    public static final String NODISPONIBLE_MOTIVO = "MOTIVO";
    public static final String NODISPONIBLE = "NoDisponible";
    public static final String LADRONDETENIDO_LADRON = "ladron";
    public static final String LADRONDETENIDO = "LadronDetenido";
    public static final String DETENERLADRON_LADRON = "ladron";
    public static final String DETENERLADRON = "DetenerLadron";
    public static final String TIEMPOLLEGADA_TIEMPO = "tiempo";
    public static final String TIEMPOLLEGADA = "TiempoLlegada";
    public static final String DESCRIPCION_COLORPELO = "colorPelo";
    public static final String DESCRIPCION_PESO = "peso";
    public static final String DESCRIPCION_ALTURA = "altura";
    public static final String DESCRIPCION = "Descripcion";
    public static final String MOTIVO_MOTIVO = "motivo";
    public static final String MOTIVO = "Motivo";
    public static final String LADRON_DESCRIPCION = "descripcion";
    public static final String LADRON = "Ladron";

    /**
     * Constructor
     */
    private PoliciaOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        try {

            // adding Concept(s)
            ConceptSchema ladronSchema = new ConceptSchema(LADRON);
            add(ladronSchema, ontologias.Policia.Ladron.class);
            ConceptSchema motivoSchema = new ConceptSchema(MOTIVO);
            add(motivoSchema, ontologias.Policia.Motivo.class);
            ConceptSchema descripcionSchema = new ConceptSchema(DESCRIPCION);
            add(descripcionSchema, ontologias.Policia.Descripcion.class);
            ConceptSchema tiempoLlegadaSchema = new ConceptSchema(TIEMPOLLEGADA);
            add(tiempoLlegadaSchema, ontologias.Policia.TiempoLlegada.class);

            // adding AgentAction(s)
            AgentActionSchema detenerLadronSchema = new AgentActionSchema(DETENERLADRON);
            add(detenerLadronSchema, ontologias.Policia.DetenerLadron.class);

    // adding AID(s)
            // adding Predicate(s)
            PredicateSchema ladronDetenidoSchema = new PredicateSchema(LADRONDETENIDO);
            add(ladronDetenidoSchema, ontologias.Policia.LadronDetenido.class);
            PredicateSchema noDisponibleSchema = new PredicateSchema(NODISPONIBLE);
            add(noDisponibleSchema, ontologias.Policia.NoDisponible.class);
            PredicateSchema disponibleSchema = new PredicateSchema(DISPONIBLE);
            add(disponibleSchema, ontologias.Policia.Disponible.class);

            // adding fields
            ladronSchema.add(LADRON_DESCRIPCION, descripcionSchema, ObjectSchema.OPTIONAL);
            motivoSchema.add(MOTIVO_MOTIVO, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            descripcionSchema.add(DESCRIPCION_ALTURA, (TermSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            descripcionSchema.add(DESCRIPCION_PESO, (TermSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            descripcionSchema.add(DESCRIPCION_COLORPELO, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            tiempoLlegadaSchema.add(TIEMPOLLEGADA_TIEMPO, (TermSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            detenerLadronSchema.add(DETENERLADRON_LADRON, ladronSchema, ObjectSchema.OPTIONAL);
            ladronDetenidoSchema.add(LADRONDETENIDO_LADRON, ladronSchema, ObjectSchema.OPTIONAL);
            noDisponibleSchema.add(NODISPONIBLE_MOTIVO, motivoSchema, ObjectSchema.OPTIONAL);
            disponibleSchema.add(DISPONIBLE_TIEMPO, tiempoLlegadaSchema, ObjectSchema.OPTIONAL);

    // adding name mappings
    // adding inheritance
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
