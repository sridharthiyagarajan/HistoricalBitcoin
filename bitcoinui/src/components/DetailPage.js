import React from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';

const DetailPage = () => {
  const { id } = useParams();
  const location = useLocation();
  const navigate = useNavigate(); // ✅ Ensure navigate is properly declared

  const item = location.state?.item;

  if (!item) {
    return <h2>No Data Found</h2>;
  }

  return (
    <div className="detail-container">
      <h1>Bitcoin Price Details</h1>
      <p><strong>Date:</strong> {item.date}</p>
      <p><strong>Price:</strong> {item.price} {item.currency}</p>
      <p><strong>Price Typesf:</strong>
        <span style={item.priceType === "HIGH" || item.priceType === "LOW" ? { fontWeight: 'bold', color: 'black' } : {}}>
                {item.priceType}
        </span>
      </p>

      {/* ✅ Back to List Button */}
      <p><button className="btn btn-secondary" onClick={() => navigate(-1)}>
        Back to List
      </button></p>
    </div>
  );
};

export default DetailPage;
