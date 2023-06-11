package com.donay.climatempo5;

public enum CondicaoClimatica {
    ec("Encoberto com Chuvas Isoladas", R.drawable.ec),
    ci("Chuvas Isoladas", R.drawable.ci),
    c("Chuva", R.drawable.c),
    in("Instável", R.drawable.in),
    pp("Poss. de Pancadas de Chuva", R.drawable.pp),
    cm("Chuva pela Manhã", R.drawable.cm),
    cn("Chuva a Noite", R.drawable.cn),
    pt("Pancadas de Chuva a Tarde", R.drawable.pt),
    pm("Pancadas de Chuva pela Manhã", R.drawable.pm),
    np("Nublado e Pancadas de Chuva", R.drawable.np),
    pc("Pancadas de Chuva", R.drawable.pc),
    pn("Parcialmente Nublado", R.drawable.pn),
    cv("Chuvisco", R.drawable.cv),
    ch("Chuvoso", R.drawable.ch),
    t("Tempestade", R.drawable.t),
    ps("Predomínio de Sol", R.drawable.ps),
    e("Encoberto", R.drawable.e),
    n("Nublado", R.drawable.n),
    cl("Céu Claro", R.drawable.ps),
    nv("Nevoeiro", R.drawable.nv),
    g("Geada", R.drawable.g),
    ne("Neve", R.drawable.ne),
    nd("Não Definido", R.drawable.nd),
    pnt("Pancadas de Chuva a Noite", R.drawable.pnt),
    psc("Possibilidade de Chuva", R.drawable.psc),
    pcm("Possibilidade de Chuva pela Manhã", R.drawable.pcm),
    pct("Possibilidade de Chuva a Tarde", R.drawable.pct),
    pcn("Possibilidade de Chuva a Noite", R.drawable.pcn),
    npt("Nublado com Pancadas a Tarde", R.drawable.npt),
    npn("Nublado com Pancadas a Noite", R.drawable.npn),
    ncn("Nublado com Poss. de Chuva a Noite", R.drawable.ncn),
    nct("Nublado com Poss. de Chuva a Tarde", R.drawable.nct),
    ncm("Nubl. c/ Poss. de Chuva pela Manhã", R.drawable.ncm),
    npm("Nublado com Pancadas pela Manhã", R.drawable.npm),
    npp("Nublado com Possibilidade de Chuva", R.drawable.npp),
    vn("Variação de Nebulosidade", R.drawable.vn),
    ct("Chuva a Tarde", R.drawable.ct),
    ppn("Poss. de Panc. de Chuva a Noite", R.drawable.ppn),
    ppt("Poss. de Panc. de Chuva a Tarde", R.drawable.ppt),
    ppm("Poss. de Panc. de Chuva pela Manhã", R.drawable.ppm);

    private String descricao;
    private int imagem;

    CondicaoClimatica(String descricao, int imagem) {
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getImagem() {
        return imagem;
    }

    public static int getImagemBySigla(String sigla) {
        for (CondicaoClimatica condicao : CondicaoClimatica.values()) {
            if (condicao.name().equalsIgnoreCase(sigla)) {
                return condicao.getImagem();
            }
        }
        return 0;
    }
}
