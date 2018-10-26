package models;

public class Package {
    Long id; // id
    Long size; // wielkosc
    String sizeUnit; // jednostkaWielkosci
    Long ean; // kodEAN
    String category; // kategoriaDostepnosci
    Boolean removed; // skasowane
    String euNumber; // numerEu
    String parallelDistributor; // dystrybutorRownolegly

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) {
        this.ean = ean;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public String getEuNumber() {
        return euNumber;
    }

    public void setEuNumber(String euNumber) {
        this.euNumber = euNumber;
    }

    public String getParallelDistributor() {
        return parallelDistributor;
    }

    public void setParallelDistributor(String parallelDistributor) {
        this.parallelDistributor = parallelDistributor;
    }

}
