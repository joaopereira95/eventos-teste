import { Instituicao } from "./Instituicao.model";

export interface Evento {
    nome: string;
    instituicao: Instituicao;
    dataInicial: Date;
    dataFinal: Date;
    ativo: boolean;
    finalizado: boolean;
}