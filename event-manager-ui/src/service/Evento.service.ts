import axios from 'axios';
import { Evento } from '../model/Evento.model';
import { CadastroEvento } from '../model/CadastroEvento.model';

const baseUrl = 'http://localhost:8080';

export async function cadastrarEvento(cadastroEvento: CadastroEvento): Promise<Evento> {
  try {
    const response = await axios.post<Evento>(`${baseUrl}/eventos`, cadastroEvento);
    return response.data;
  } catch (error) {
    throw new Error(`Erro ao cadastrar evento: ${error}`);
  }
}

export async function listarEventos(): Promise<Evento[]> {
  try {
    const response = await axios.get<Evento[]>(`${baseUrl}/eventos`);
    return response.data;
  } catch (error) {
    throw new Error(`Erro ao listar eventos: ${error}`);
  }
}
