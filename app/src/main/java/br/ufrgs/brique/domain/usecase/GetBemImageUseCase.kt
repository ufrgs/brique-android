package br.ufrgs.brique.domain.usecase

import kotlinx.coroutines.Deferred
import java.io.File

interface GetBemImageUseCase {
    fun getImage(nrSequencia: String): Deferred<File>
}