import React from 'react';

const FormComponent = ({ startDate, endDate, useOfflineData, currency, setStartDate, setEndDate, setUseOfflineData, setCurrency, handleSubmit, loading }) => {
  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label className="form-label">Start Date</label>
        <input
          type="date"
          className="form-control"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-label">End Date</label>
        <input
          type="date"
          className="form-control"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
          required
        />
      </div>
      <div className="mb-3">
        <label className="form-check-label">Use Offline Data</label>
        <input
          type="checkbox"
          className="form-check-input"
          checked={useOfflineData}
          onChange={() => setUseOfflineData(!useOfflineData)}
        />
      </div>
      <div className="mb-3">
        <label className="form-label">Currency</label>
        <input
          type="text"
          className="form-control"
          value={currency}
          onChange={(e) => setCurrency(e.target.value)}
          placeholder="INR, USD, etc."
          required
        />
      </div>
      <button type="submit" className="btn btn-primary" disabled={loading}>Get History</button>
    </form>
  );
};

export default FormComponent;