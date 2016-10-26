package com.act.biointerpretation.networkanalysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MetabolismNetwork.class, name = "MetabolismNetwork")
})
public interface ImmutableNetwork {

  public NetworkNode getNodeByUID(Integer uid);

  public NetworkNode getNodeByInchi(String inchi);

  public Optional<NetworkNode> getNodeOptionByInchi(String inchi);

  public List<NetworkNode> getNodesByMass(Double mass);

  @JsonIgnore
  public Collection<NetworkNode> getNodes();

  /**
   * Get all edges from the graph.
   *
   * @return An unmodifiable collection of the graph's edges.
   */
  public Collection<NetworkEdge> getEdges();

  /**
   * Get all nodes that are one step forward from this node. These are predicted products of reactions that have this
   * node as a substrate.
   *
   * @param node The starting node.
   * @return The list of potential product nodes.
   */
  public List<NetworkNode> getDerivatives(NetworkNode node);

  /**
   * Get all nodes that are one step before this node. These are substrates of reactions that are predicted to produce
   * this node as a product.
   *
   * @param node The starting node.
   * @return The list of potential substrate nodes.
   */
  public List<NetworkNode> getPrecursors(NetworkNode node);

  public void writeToJsonFile(File outputFile) throws IOException;
}
