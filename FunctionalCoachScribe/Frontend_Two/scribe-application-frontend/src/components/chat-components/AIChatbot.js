import React, { useEffect } from 'react';

export const AIChatbot = () => {
  useEffect(() => {
    const script = document.createElement('script');

    script.onload = () => {
      window.voiceflow.chat.load({
        verify: { projectID: '653d59728020960007927f64' },
        url: 'https://general-runtime.voiceflow.com',
        versionID: 'production'
      });
    };

    script.src = 'https://cdn.voiceflow.com/widget/bundle.mjs';
    script.type = 'text/javascript';

    document.getElementsByTagName('head')[0].appendChild(script);

    return () => {
      // Cleanup if needed (e.g., remove the script when the component unmounts)
      document.getElementsByTagName('head')[0].removeChild(script);
    };
  }, []); // Empty dependency array ensures that this effect runs once after the initial render

  return <div>Your chatbot content goes here</div>;
};
