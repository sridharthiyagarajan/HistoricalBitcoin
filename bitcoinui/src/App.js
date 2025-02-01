import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from 'axios';
import FormComponent from './components/FormComponent';
import HistoryList from './components/HistoryList';
import DetailPage from './components/DetailPage';
import './styles.css';

const username = 'admin';
const password = 'admin';
const encodedCredentials = btoa(`${username}:${password}`);

const App = () => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [useOfflineData, setUseOfflineData] = useState(false);
  const [currency, setCurrency] = useState('');
  const [historyData, setHistoryData] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);

  const API_URL =
  window.location.hostname === "localhost"
    ? "http://localhost:8080"
    : "http://bitcoinservice:8080";


    try {
      const response = await axios.get(`${API_URL}/bitcoin-history`, {
        params: { startDate, endDate, useOfflineData, currency },
        headers: {
          'Authorization': `Basic ${encodedCredentials}`,
          'Content-Type': 'application/json'
        }
      });
      setHistoryData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }

    setLoading(false);
  };

  return (
    <Router>
      <Routes>
        <Route 
          path="/" 
          element={
            <div>
              <h1>Bitcoin History</h1>
              <FormComponent
                startDate={startDate}
                endDate={endDate}
                useOfflineData={useOfflineData}
                currency={currency}
                setStartDate={setStartDate}
                setEndDate={setEndDate}
                setUseOfflineData={setUseOfflineData}
                setCurrency={setCurrency}
                handleSubmit={handleSubmit}
                loading={loading}
              />
              <HistoryList historyData={historyData} currency={currency} />
            </div>
          } 
        />
        <Route path="/detail/:id" element={<DetailPage />} />
      </Routes>
    </Router>
  );
};

export default App;
