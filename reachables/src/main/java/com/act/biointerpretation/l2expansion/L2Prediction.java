package com.act.biointerpretation.l2expansion;

import com.act.biointerpretation.sars.Sar;
import com.act.biointerpretation.sars.SerializableReactor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single predicted reaction from the L2 expansion
 */
public class L2Prediction {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @JsonProperty("_id")
  private Integer id;

  @JsonProperty("substrates")
  private List<L2PredictionChemical> substrates;

  @JsonProperty("ro")
  private SerializableReactor reactor;

  @JsonProperty("sar")
  private List<Sar> sars;

  @JsonProperty("products")
  private List<L2PredictionChemical> products;

  @JsonProperty("reactions_ro_match")
  private List<Long> reactionsRoMatch;

  @JsonProperty("reactions_no_ro_match")
  private List<Long> reactionsNoRoMatch;

  // Necessary for JSON reading
  private L2Prediction() {
  }

  public L2Prediction(L2Prediction template) {
    this.id = template.id;

    this.substrates = new ArrayList<>(template.substrates.size());
    for (L2PredictionChemical substrate : template.substrates) {
      this.substrates.add(new L2PredictionChemical(substrate));
    }

    this.reactor = new SerializableReactor(template.reactor);

    this.products = new ArrayList<>(template.products.size());
    for (L2PredictionChemical product : template.products) {
      this.products.add(new L2PredictionChemical(product));
    }

    this.reactionsRoMatch = new ArrayList<Long>(template.reactionsRoMatch);
    this.reactionsNoRoMatch = new ArrayList<Long>(template.reactionsNoRoMatch);

    this.sars = template.getSars();
  }

  public L2Prediction(Integer id,
                      List<L2PredictionChemical> substrates,
                      SerializableReactor reactor,
                      List<Sar> sars,
                      List<L2PredictionChemical> products) {
    this.id = id;
    this.substrates = substrates;
    this.products = products;
    this.reactor = reactor;
    this.sars = sars;
    this.reactionsRoMatch = new ArrayList<Long>();
    this.reactionsNoRoMatch = new ArrayList<Long>();
  }

  @JsonIgnore
  public int getReactionCount() {
    return reactionsRoMatch.size() + reactionsNoRoMatch.size();
  }

  @JsonIgnore
  public List<String> getSubstrateInchis() {
    List<String> inchis = new ArrayList<>();
    for (L2PredictionChemical chemical : getSubstrates()) {
      inchis.add(chemical.getInchi());
    }
    return inchis;
  }

  @JsonIgnore
  public List<Long> getSubstrateIds() {
    List<Long> ids = new ArrayList<>();
    for (L2PredictionChemical chemical : getSubstrates()) {
      if (chemical.hasId()) {
        ids.add(chemical.getId());
      }
    }
    return ids;
  }

  @JsonIgnore
  public List<String> getSubstrateNames() {
    List<String> names = new ArrayList<>();
    for (L2PredictionChemical chemical : getSubstrates()) {
      if (chemical.hasName()) {
        names.add(chemical.getName());
      }
    }
    return names;
  }

  @JsonIgnore
  public List<String> getProductInchis() {
    List<String> inchis = new ArrayList<>();
    for (L2PredictionChemical chemical : getProducts()) {
      inchis.add(chemical.getInchi());
    }
    return inchis;
  }

  @JsonIgnore
  public List<Long> getProductIds() {
    List<Long> ids = new ArrayList<>();
    for (L2PredictionChemical chemical : getProducts()) {
      if (chemical.hasId()) {
        ids.add(chemical.getId());
      }
    }
    return ids;
  }

  @JsonIgnore
  public List<String> getProductNames() {
    List<String> names = new ArrayList<>();
    for (L2PredictionChemical chemical : getProducts()) {
      if (chemical.hasName()) {
        names.add(chemical.getName());
      }
    }
    return names;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<L2PredictionChemical> getSubstrates() {
    return substrates;
  }

  public void setSubstrates(List<L2PredictionChemical> substrates) {
    this.substrates = substrates;
  }

  public List<L2PredictionChemical> getProducts() {
    return products;
  }

  public void setProducts(List<L2PredictionChemical> products) {
    this.products = products;
  }

  public SerializableReactor getReactor() {
    return reactor;
  }

  public List<Long> getReactionsRoMatch() {
    return reactionsRoMatch;
  }

  public void setReactionsRoMatch(List<Long> reactionsRoMatch) {
    this.reactionsRoMatch = reactionsRoMatch;
  }

  public List<Long> getReactionsNoRoMatch() {
    return reactionsNoRoMatch;
  }

  public void setReactionsNoRoMatch(List<Long> reactionsNoRoMatch) {
    this.reactionsNoRoMatch = reactionsNoRoMatch;
  }

  public List<Sar> getSars() {
    return sars;
  }

  public void setSars(List<Sar> sar) {
    this.sars = sar;
  }
}
