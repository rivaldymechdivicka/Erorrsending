import { useCallback, useState } from "react";
import ErrorBoundary from "./ErrorBoundary";

function App() {
  // State untuk menyimpan komponen yang akan dirender
  const [comp, setComp] = useState(null);

  // Fungsi handleClick yang akan dipanggil saat tombol diklik
  const handleClick = useCallback(() => {
    // Menyimpan objek yang menyebabkan kesalahan pada state `comp`
    setComp({"something": "is wrong"})
  }, []);

  return (
    <ErrorBoundary>
      <div style={{ height: '100vh', width: '100vw', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
        <button onClick={handleClick}>Do something that will cause error</button>
        {comp}
      </div>
    </ErrorBoundary>
  );
}

export default App;
