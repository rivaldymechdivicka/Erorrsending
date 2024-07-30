import React from 'react';
import axios from 'axios';

// ErrorBoundary adalah komponen yang digunakan untuk menangkap dan menangani kesalahan yang terjadi di dalam komponen anaknya.
class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    // State yang digunakan untuk menentukan apakah terjadi kesalahan atau tidak
    this.state = { hasError: false };
  }

  // Metode ini dipanggil ketika terjadi kesalahan di salah satu komponen anak
  static getDerivedStateFromError() {
    // Mengupdate state untuk menampilkan UI fallback jika terjadi kesalahan
    return { hasError: true };
  }

  // Metode ini dipanggil setelah terjadi kesalahan, memungkinkan kita untuk melakukan sesuatu dengan informasi kesalahan
  componentDidCatch(error, info) {
    // Payload yang berisi informasi kesalahan yang akan dikirim ke server untuk log
    const payload = {
      error: error.message,  
      stack: error.stack,   
      componentStack: info.componentStack 
    };

    // Mengirim data kesalahan ke server menggunakan axios
    axios.post('http://127.0.0.1:8091/sample/logError', payload)
      .then(response => {
        // Menampilkan data yang diterima dari server setelah mengirimkan data kesalahan
        console.log('Success:', response.data);
      })
      .catch(err => {
        // Menangani kesalahan jika pengiriman data kesalahan gagal
        console.error('Failed to send error report:', err);
      });
  }

  render() {
    // Jika terjadi kesalahan, tampilkan UI fallback
    if (this.state.hasError) {
      return <h1>Something went wrong.</h1>;
    }
    // Jika tidak terjadi kesalahan, render komponen anak
    return this.props.children;
  }
}

export default ErrorBoundary;
