package br.com.projeto.api.categoria;

public record CategoriaCreateResponse(Long fielId, String nome) {
    public CategoriaCreateResponse(CategoriaEntity categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
