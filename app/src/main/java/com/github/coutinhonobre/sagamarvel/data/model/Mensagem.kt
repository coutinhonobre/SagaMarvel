package com.github.coutinhonobre.sagamarvel.data.model

data class Mensagem(
    var tipo: TipoMensagem = TipoMensagem.ERROR,
    var descricao: String = ""
)