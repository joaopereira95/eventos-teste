import axios from 'axios';
import { Instituicao } from '../model/Instituicao.model';

const baseUrl = 'http://localhost:8080';

export async function listarInstituicoes(): Promise<Instituicao[]> {
  try {
    const response = await axios.get<Instituicao[]>(`${baseUrl}/instituicoes`);
    return response.data;
  } catch (error) {
    throw new Error(`Erro ao listar instituicoes: ${error}`);
  }
}
