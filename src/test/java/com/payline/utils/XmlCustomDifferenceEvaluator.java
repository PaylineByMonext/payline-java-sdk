package com.payline.utils;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;
import org.xmlunit.diff.DifferenceEvaluator;

public class XmlCustomDifferenceEvaluator implements DifferenceEvaluator {

    @Override
    public ComparisonResult evaluate(final Comparison comparison, final ComparisonResult comparisonResult) {
        if (comparisonResult == ComparisonResult.EQUAL) {
            return comparisonResult;
        }

        // On ne check pas les namespace ni l'ordre des éléments
        if (ComparisonType.NAMESPACE_URI.equals(comparison.getType())
                ||ComparisonType.NAMESPACE_PREFIX.equals(comparison.getType())
                ||ComparisonType.CHILD_NODELIST_SEQUENCE.equals(comparison.getType())) {
            return ComparisonResult.SIMILAR;
        }

        // On Les valeur Null. On accept qu'un élément soit NULL si l'élément de comparaison est xsi:nil="true"
        if (ComparisonType.CHILD_LOOKUP.equals(comparison.getType())
                && comparison.toString().startsWith("Expected child 'null' but was ")) {
            return checkIfNodeIsNull(comparison, comparisonResult);
        }
        // On Les valeur Null. On accept qu'un élément soit NULL si l'élément de comparaison est xsi:nil="true"
        if (ComparisonType.CHILD_NODELIST_LENGTH.equals(comparison.getType())) {
            return checkNodeSizeWithNullChild(comparison, comparisonResult);
        }

        final Node controlNode = comparison.getControlDetails().getTarget();
        if (controlNode instanceof Attr) {
            final Attr attr = (Attr) controlNode;
            if (attr.getName().equals(comparisonResult)) {
                return ComparisonResult.SIMILAR;
            }
        }
        return comparisonResult;
    }

    private ComparisonResult checkIfNodeIsNull(final Comparison comparison, final ComparisonResult comparisonResult) {

        // Si l'élément de comparaison est NULL ET que lélément de TEST n'est pas NULL mais comporte la mention [xsi:nil="true"]
        // Alors c'est OK
        if (comparison.getControlDetails().getTarget() == null
                && (comparison.getTestDetails().getTarget() != null && checkAttributSimilar(comparison.getTestDetails().getTarget(), "xsi:nil", "true"))) {
            return ComparisonResult.SIMILAR;
        }

        return comparisonResult;
    }

    /**
     * Ici on check la différence du nombre d'élément des éléments du noeud enfant.
     * Si on Noeud à plus d'élement, mais que les élément en plus sont NULL [xsi:nil="true"] alors c'est OK
     * @param comparison
     * @param comparisonResult
     * @return
     */
    private ComparisonResult checkNodeSizeWithNullChild(final Comparison comparison, final ComparisonResult comparisonResult) {

        final String controlTest = comparison.getControlDetails().getTarget().getTextContent();
        final String testText = comparison.getTestDetails().getTarget().getTextContent();
        System.out.println("comparison Size Node  control = [" + controlTest + "]  - Test = [" + testText + "]");

        if (StringUtils.equals(controlTest, testText)) {
            return ComparisonResult.SIMILAR;
        }

        return comparisonResult;
    }

    private boolean checkAttributSimilar(final Node node, final String key, final String value) {

        final NamedNodeMap attributes = node.getAttributes();
        if (attributes != null && attributes.getLength() > 0 ) {
            return StringUtils.equalsIgnoreCase(attributes.getNamedItem(key).getNodeValue(), value);
        }

        return false;
    }

}
