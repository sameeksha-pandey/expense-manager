import { useState } from 'react';
import api from '../services/api';

function CsvUpload({ onUploadComplete }) {

    const [file, setFile] = useState(null);
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const [uploading, setUploading] = useState(false);

    const handleFileChange = (event) => {

        const selectedFile = event.target.files[0];

        setMessage('');
        setError('');

        if (!selectedFile) {
            setFile(null);
            return;
        }

        if (!selectedFile.name.toLowerCase().endsWith('.csv')) {
            setFile(null);
            setError('Please select a CSV file.');
            return;
        }

        setFile(selectedFile);
    };

    const handleUpload = async () => {

        if (!file) {
            setError('Please select a CSV file first.');
            return;
        }

        setMessage('');
        setError('');
        setUploading(true);

        const formData = new FormData();

        formData.append('file', file);

        try {

            const response = await api.post(
                '/expenses/upload-csv',
                formData
            );

            setMessage(
                response.data || 'CSV uploaded successfully.'
            );

            setFile(null);

            if (onUploadComplete) {
                onUploadComplete();
            }

        } catch (error) {

            console.error('CSV upload failed:', error);

            setError(
                error.response?.data ||
                'CSV upload failed. Please check the file.'
            );

        } finally {

            setUploading(false);
        }
    };

    return (
        <div className="dashboard-section">

            <h2>Upload Expenses via CSV</h2>

            <input
                className="file-input"
                type="file"
                accept=".csv"
                onChange={handleFileChange}
            />

            {file && (
                <p>
                    Selected file: <strong>{file.name}</strong>
                </p>
            )}

            <button
                className="primary-button"
                type="button"
                onClick={handleUpload}
                disabled={!file || uploading}
            >
                {uploading
                    ? 'Uploading...'
                    : 'Upload CSV'}
            </button>

            {message && (
                <p className="success-message">
                    {message}
                </p>
            )}

            {error && (
                <p className="error-message">
                    {error}
                </p>
            )}

        </div>
    );
}

export default CsvUpload;