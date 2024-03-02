import React, { useEffect, useState } from 'react';
import { Container } from '@mui/material';

import { cadastrarEvento, listarEventos } from './service/Evento.service';
import { CadastroEvento } from './model/CadastroEvento.model';
import { Evento } from './model/Evento.model';
import SnackbarComponent from './component/SnackbarComponent';
import TabelaEventos from './component/eventos/TabelaEventos';
import Cadastro from './component/eventos/Cadastro';
import BackdropComponent from './component/BackdropComponent';

function App() {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [messageText, setMessageText] = useState('');
  const [eventos, setEventos] = useState<Evento[]>([]);

  const MSG_EVENTO_CADASTRADO_COM_SUCESSO = 'O evento foi cadastrado com sucesso!';
  const MSG_ERRO_CADASTRO_EVENTO = 'Ocorreu um erro ao cadastrar o evento.';
  const MSG_ERRO_AO_BUSCAR_EVENTOS = 'Ocorreu um erro ao buscar os eventos.';

  const closeLoading = () => {
    setLoading(false);
  };

  const openLoading = () => {
    setLoading(true);
  };

  const closeErrorMessage = () => {
    setErrorMessage(false);
  };
  
  const openErrorMessage = (message: string) => {
    setMessageText(message);
    setErrorMessage(true);   
  };

  const closeSuccessMessage = () => {
    setSuccessMessage(false);
  };
  
  const openSuccessMessage = (message: string) => {
    setMessageText(message);
    setSuccessMessage(true);   
  };
  
  const handleSubmit = (novoEvento: CadastroEvento, limparFormulario: () => void) => {
    cadastrarEvento(novoEvento)
      .then(() => {
        openSuccessMessage(MSG_EVENTO_CADASTRADO_COM_SUCESSO);      
        buscarEventos();
        limparFormulario();
      })
      .catch(() => {
        openErrorMessage(MSG_ERRO_CADASTRO_EVENTO);
      })
      .finally(closeLoading);

  };
  
  const buscarEventos = () => {
    listarEventos()
      .then(response => {
        setEventos(response);
      })
      .catch(() => {
        openErrorMessage(MSG_ERRO_AO_BUSCAR_EVENTOS);
      });
  };

  useEffect(() => {
    buscarEventos();
  }, []);

  return (
    <Container>
      <BackdropComponent open={loading} />
      <SnackbarComponent open={errorMessage} message={messageText} onClose={closeErrorMessage} severity="error" />
      <SnackbarComponent open={successMessage} message={messageText} onClose={closeSuccessMessage} severity="success" />
      <Cadastro onSubmit={handleSubmit} openErrorMessage={openErrorMessage} openLoading={openLoading} />

      {eventos.length > 0 &&
        <TabelaEventos eventos={eventos} />
      }
    </Container>
  );
}

export default App;