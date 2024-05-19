import React, { useEffect } from 'react';

export const AIChatbot = () => {
  useEffect(() => {
    const script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://cdn.voiceflow.com/widget/bundle.mjs';
    script.onload = () => {
      window.voiceflow.chat.load({
        verify: { projectID: '653d59728020960007927f64' },
        url: 'https://general-runtime.voiceflow.com',
        versionID: 'production'
      });
    };

    document.getElementsByTagName('head')[0].appendChild(script);

    // Cleanup the script when the component unmounts
    return () => {
      document.getElementsByTagName('head')[0].removeChild(script);
    };
  }, []);

  return (
    // Your component JSX
    <div>
      {/* Your component content */}
    </div>
  );
};
