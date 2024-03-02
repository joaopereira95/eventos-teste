import React, { useState, useEffect } from 'react';
import { TextField, Button, Grid, Paper, FormControl, InputLabel, Select, MenuItem, Typography } from '@mui/material';
import DatePicker from 'react-datepicker';
import { ptBR } from 'date-fns/locale';
import 'react-datepicker/dist/react-datepicker.css';
import { CadastroEvento } from '../../model/CadastroEvento.model';
import { Instituicao } from '../../model/Instituicao.model';
import { listarInstituicoes } from '../../service/Instituicao.service';

import "./customDatePickerWidth.css";

interface CadastroProps {
  onSubmit: (evento: CadastroEvento, limparFormulario: () => void) => void;
  openErrorMessage: (message: string) => void;
  openLoading: () => void;
}
const Cadastro: React.FC<CadastroProps> = ({ onSubmit, openErrorMessage, openLoading }) => {
  const [novoEvento, setNovoEvento] = useState<CadastroEvento>({
    nome: '',
    instituicao: null,
    dataInicial: null,
    dataFinal: null
  });
  const [instituicoes, setInstituicoes] = useState<Instituicao[]>([]);

  useEffect(() => {
    const buscarInstituicoes = async () => {
      try {
        const response = await listarInstituicoes();
        setInstituicoes(response);
      } catch (error) {
        openErrorMessage('Erro ao buscar as instituições.');
        console.error('Erro ao buscar as instituições:', error);
      }
    };

    buscarInstituicoes();
  }, []);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setNovoEvento({ ...novoEvento, [name]: value });
  };

  const handleDateChange = (date: Date | null, field: string) => {
    setNovoEvento({ ...novoEvento, [field]: date });
  };

  const handleInstituicaoChange = (event: any) => {
    setNovoEvento({ ...novoEvento, instituicao: event.target.value as number });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();

    if (!validarFormulario()) {
      return;
    }

    openLoading();
    
    onSubmit(novoEvento, limparFormulario);
  };

  const validarFormulario = () => {
    if (!novoEvento.nome) {
      openErrorMessage('Preencha o nome do evento');
      return false;
    }

    if (!novoEvento.instituicao) {
      openErrorMessage('Preencha a instituição do evento');
      return false;
    }

    if (!novoEvento.dataInicial || !novoEvento.dataFinal) {
      openErrorMessage('Preencha a vigência do evento');
      return false;
    }

    if (novoEvento.dataInicial >= novoEvento.dataFinal) {
      openErrorMessage('A data final deve ser após a data inicial');
      return false;
    }
    
    return true;
  };

  const limparFormulario = () => {
    setNovoEvento({
      nome: '',
      instituicao: null,
      dataInicial: null,
      dataFinal: null
    });
  }

  return (
    <>
      <Typography variant="h4" align="center" gutterBottom>Cadastro de Eventos</Typography>
      <Paper variant="outlined" style={{ padding: '20px', marginBottom: '20px' }}>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="Nome"
                name="nome"
                value={novoEvento.nome}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12} md={12}>
              <FormControl fullWidth>
                <InputLabel>Instituição</InputLabel>
                <Select
                  value={novoEvento.instituicao}
                  onChange={handleInstituicaoChange}
                >
                  {instituicoes.map((instituicao) => (
                    <MenuItem key={instituicao.id} value={instituicao.id}>
                      {instituicao.nome}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={6} md={6}>
              <div className="customDatePickerWidth">
                <DatePicker
                    locale={ptBR}
                    selected={novoEvento.dataInicial}
                    showYearDropdown
                    showMonthDropdown
                    placeholderText='DD/MM/AAAA HH:mm'
                    customInput={<TextField fullWidth label='Data inicial' autoComplete='off' />}
                    dateFormat="dd/MM/yyyy HH:mm"
                    timeFormat="HH:mm"
                    showTimeSelect
                    onChange={(date) => handleDateChange(date, 'dataInicial')}
                />
              </div>
            </Grid>
            <Grid item xs={6} md={6}>
              <div className="customDatePickerWidth">
                <DatePicker
                    locale={ptBR}
                    selected={novoEvento.dataFinal}
                    showYearDropdown
                    showMonthDropdown
                    placeholderText='DD/MM/AAAA HH:mm'
                    customInput={<TextField fullWidth label='Data final' autoComplete='off' />}
                    dateFormat="dd/MM/yyyy HH:mm"
                    timeFormat="HH:mm"
                    showTimeSelect
                    onChange={(date) => handleDateChange(date, 'dataFinal')}
                />
              </div>
            </Grid>
          </Grid>
          <Button type="submit" variant="contained" color="primary" style={{ marginTop: '20px' }}>Cadastrar Evento</Button>
        </form>
      </Paper>
    </>
  );
};

export default Cadastro;