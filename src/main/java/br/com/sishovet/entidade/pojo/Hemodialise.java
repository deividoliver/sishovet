/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.entidade.pojo;


import org.bson.types.ObjectId;
/**
 *
 * @author DeividOliveira
 */
public class Hemodialise {
    private ObjectId id;
    
    private Double hemacias;   //X 106/mm3
    private Double hemoglobina; //g/dL
    private Double hematocrito; //%
    private Double VCM; //fL
    private Double HCM; //pg
    private Double CHCM; //g/dL
    private Double RDW; //%
    private Double plaquetas; //x 103/mm3
    private Double VPM; //fL
    private Double PDW;
    private Double PCT; //%
    private Double leucócitos_totais; //x 103/mm3

    
    public Hemodialise() {
    }

    public Hemodialise(Double hemacias, Double hemoglobina, Double hematocrito, Double VCM, Double HCM, Double CHCM, Double RDW, Double plaquetas, Double VPM, Double PDW, Double PCT, Double leucócitos_totais) {
        this.hemacias = hemacias;
        this.hemoglobina = hemoglobina;
        this.hematocrito = hematocrito;
        this.VCM = VCM;
        this.HCM = HCM;
        this.CHCM = CHCM;
        this.RDW = RDW;
        this.plaquetas = plaquetas;
        this.VPM = VPM;
        this.PDW = PDW;
        this.PCT = PCT;
        this.leucócitos_totais = leucócitos_totais;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Double getHemacias() {
        return hemacias;
    }

    public void setHemacias(Double hemacias) {
        this.hemacias = hemacias;
    }

    public Double getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(Double hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public Double getHematocrito() {
        return hematocrito;
    }

    public void setHematocrito(Double hematocrito) {
        this.hematocrito = hematocrito;
    }

    public Double getVCM() {
        return VCM;
    }

    public void setVCM(Double VCM) {
        this.VCM = VCM;
    }

    public Double getHCM() {
        return HCM;
    }

    public void setHCM(Double HCM) {
        this.HCM = HCM;
    }

    public Double getCHCM() {
        return CHCM;
    }

    public void setCHCM(Double CHCM) {
        this.CHCM = CHCM;
    }

    public Double getRDW() {
        return RDW;
    }

    public void setRDW(Double RDW) {
        this.RDW = RDW;
    }

    public Double getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(Double plaquetas) {
        this.plaquetas = plaquetas;
    }

    public Double getVPM() {
        return VPM;
    }

    public void setVPM(Double VPM) {
        this.VPM = VPM;
    }

    public Double getPDW() {
        return PDW;
    }

    public void setPDW(Double PDW) {
        this.PDW = PDW;
    }

    public Double getPCT() {
        return PCT;
    }

    public void setPCT(Double PCT) {
        this.PCT = PCT;
    }

    public Double getLeucócitos_totais() {
        return leucócitos_totais;
    }

    public void setLeucócitos_totais(Double leucócitos_totais) {
        this.leucócitos_totais = leucócitos_totais;
    }

    
    
}