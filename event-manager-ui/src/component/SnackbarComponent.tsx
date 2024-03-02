import React from 'react';
import { Snackbar, Alert } from '@mui/material';

interface SnackbarProps {
  open: boolean;
  message: string;
  onClose: () => void;
  severity: 'error' | 'success' | 'info' | 'warning';
}

const SnackbarComponent: React.FC<SnackbarProps> = ({ open, message, onClose, severity }) => {
  return (
    <Snackbar open={open} autoHideDuration={6000} onClose={onClose}>
      <Alert
        onClose={onClose}
        severity={severity}
        variant="filled"
        sx={{ width: '100%' }}
      >
        {message}
      </Alert>
    </Snackbar>
  );
};

export default SnackbarComponent;
