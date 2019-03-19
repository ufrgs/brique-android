package br.ufrgs.brique.data.repository.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class MuralResponse(
    val items: List<Bem>,
    val _meta: Meta
)

@Parcelize
class Bem(
    val NrSeqItem: String,
    val NrPatrimonio: String,
    val Nome: String,
    val Descricao: String? = null,
    val DataCadastro: String? = null,
    val NomeOrgaoOrigem: String? = null,
    val NomeOrgaoDestino: String? = null,
    val NomePessoaCadastro: String? = null,
    val Editar: String? = null,
    val Solicitar: String? = null,
    val CodOrgaoOrigem: String? = null,
    val DataTransferencia: String? = null,
    val CodOrgaoDestino: String? = null,
    val DataRemocao: String? = null,
    val CodPessoaCadastro: String? = null
) : Parcelable