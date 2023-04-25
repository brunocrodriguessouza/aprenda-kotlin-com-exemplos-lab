// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario(var nome: String, var email: String, var telefone: String) {
    val inscricoes = mutableListOf<Formacao>()
    
    fun inscrever(formacao: Formacao) {
        if (!inscricoes.contains(formacao)) {
            inscricoes.add(formacao)
            formacao.adicionarInscrito(this)
        }
    }
    
    fun cancelarInscricao(formacao: Formacao) {
        if (inscricoes.contains(formacao)) {
            inscricoes.remove(formacao)
            formacao.removerInscrito(this)
        }
    }
}

data class Modulo(val nome: String, val duracao: Int)

data class Curso(val nome: String, var modulos: List<Modulo>) {
    val duracao: Int
        get() = modulos.sumOf { it.duracao }
}

data class Formacao(val nome: String, var cursos: List<Curso>, var nivel: Nivel) {
    val inscritos = mutableListOf<Usuario>()
    
    val duracao: Int
        get() = cursos.sumOf { it.duracao }
    
    fun adicionarInscrito(usuario: Usuario) {
        if (!inscritos.contains(usuario)) {
            inscritos.add(usuario)
            usuario.inscrever(this)
        }
    }
    
    fun removerInscrito(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            inscritos.remove(usuario)
            usuario.cancelarInscricao(this)
        }
    }
    
    fun adicionarCurso(curso: Curso) {
        cursos += curso
    }
    
    fun removerCurso(curso: Curso) {
        cursos -= curso
    }
}

fun main() {
    val usuario1 = Usuario("João", "joao@email.com", "")
    val usuario2 = Usuario("Maria", "maria@email.com", "")
    
    val modulo1 = Modulo("Introdução", 60)
    val modulo2 = Modulo("Fundamentos", 120)
    
    val curso1 = Curso("Java Básico", listOf(modulo1, modulo2))
    
    val formacao1 = Formacao("Desenvolvimento Java", listOf(curso1), Nivel.INTERMEDIARIO)
    
    formacao1.adicionarInscrito(usuario1)
    formacao1.adicionarInscrito(usuario2)
    
    println("Formação ${formacao1.nome} tem ${formacao1.inscritos.size} inscritos: ${formacao1.inscritos.map { it.nome }}")
    
    usuario1.cancelarInscricao(formacao1)
    
    println("Formação ${formacao1.nome} tem ${formacao1.inscritos.size} inscritos: ${formacao1.inscritos.map { it.nome }}")
}
