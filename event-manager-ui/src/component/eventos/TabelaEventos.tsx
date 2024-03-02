import React, { useState } from 'react';
import { Typography, Paper } from '@mui/material';
import { DataGrid, GridColDef, GridPaginationModel } from '@mui/x-data-grid';
import { Evento } from '../../model/Evento.model';

interface TabelaEventosProps {
  eventos: Evento[];
}

const TabelaEventos: React.FC<TabelaEventosProps> = ({ eventos }) => {
const [paginationModel] = useState<GridPaginationModel>({page: 0, pageSize: 10});
  const columns: GridColDef[] = [
    { field: 'id', headerName: 'Código', flex: 1 },
    { field: 'nome', headerName: 'Nome', flex: 1 },
    { field: 'instituicao', headerName: 'Instituição', flex: 1, valueGetter: (params) => params.row.instituicao.nome },
    { field: 'dataInicial', headerName: 'Data Inicial', flex: 1, valueFormatter: (params) => new Date(params.value as string).toLocaleString('pt-BR') },
    { field: 'dataFinal', headerName: 'Data Final', flex: 1, valueFormatter: (params) => new Date(params.value as string).toLocaleString('pt-BR') },
    { field: 'status', headerName: 'Status', flex: 1, valueGetter: (params) => params.row.ativo ? 'Em andamento' : params.row.finalizado ? 'Finalizado' : 'Agendado' },
  ];

  return (
    <>
      <Typography variant="h5" align="center" gutterBottom>Eventos cadastrados</Typography>
      <Paper variant="outlined" style={{ width: '100%', marginBottom: '20px' }}>
        <DataGrid
          rows={eventos}
          density='comfortable'
          columns={columns}
          initialState={{
            pagination: {
              paginationModel
            },
          }}
          rowSelection={false}
          pageSizeOptions={[10, 20, 50, 100]}
          localeText={{
            columnMenuLabel: 'Opções',
            columnMenuUnsort: "não classificado",
            columnMenuSortAsc: "Classificar por ordem crescente",
            columnMenuSortDesc: "Classificar por ordem decrescente",
            columnMenuFilter: "Filtro",
            columnMenuHideColumn: "Ocultar",
            columnMenuShowColumns: "Mostrar colunas",
            columnMenuManageColumns: "Selecionar colunas",
            columnHeaderSortIconLabel: 'Ordenar',
            MuiTablePagination: {
              labelRowsPerPage: 'Linhas por página',
              labelDisplayedRows: ({ from, to, count }) =>
                `${from} - ${to} de ${count}`,
            },
          }}
        />
      </Paper>
    </>
  );
};

export default TabelaEventos;
