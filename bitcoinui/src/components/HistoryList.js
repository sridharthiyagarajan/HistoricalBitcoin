import React from 'react';
import { useNavigate } from 'react-router-dom';

const HistoryList = ({ historyData, currency }) => {
  const navigate = useNavigate();

  const handleItemClick = (item, index) => {
    navigate(`/detail/${index}`, { state: { item } });
  };

  return (
    <div className="history-list-container">
      
      {historyData.length > 0 ? (
        <ul className="list-group">
          {historyData.map((item, index) => (
            <li 
              key={index} 
              className="list-group-item clickable"
              onClick={() => handleItemClick(item, index)}
            >
              <strong>Date:</strong> {item.date} <br />
              <strong>Price:</strong> 
              <span style={item.priceType === "HIGH" || item.priceType === "LOW" ? { fontWeight: 'bold' } : {}}>
                {item.price} {currency} {item.priceType}
              </span>
             
            </li>
          ))}
        </ul>
      ) : (
        <p>No data available.</p>
      )}
    </div>
  );
};

export default HistoryList;
