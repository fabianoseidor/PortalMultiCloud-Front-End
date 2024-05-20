/**
 * 
 */
"use strict";
     const handlePhone = (event) => {
    	  let input = event.target
    	  input.value = phoneMask(input.value)
    	}

    	const phoneMask = (value) => {
    	  if (!value) return ""
    	  value = value.replace(/\D/g,'')
    	  value = value.replace(/(\d{2})(\d)/,"($1) $2")
    	  value = value.replace(/(\d)(\d{4})$/,"$1-$2")
    	  return value
    	}
    	
		const handleZipCode = (event) => {
		  let input = event.target
		  input.value = zipCodeMask(input.value)
		}
		
		const zipCodeMask = (value) => {
		  if (!value) return ""
		  value = value.replace(/\D/g,'')
		  value = value.replace(/(\d{5})(\d)/,'$1-$2')
		  return value
		}
		
		
		const handleCpfCnpj = (event) => {
            let input = event.target
			 input.value = CpfCnpjCodeMask(input.value)
		}
		const CpfCnpjCodeMask = (value) => {
		   if (!value) return ""
		  const cnpjCpf = value.replace(/\D/g, '');
		  if (cnpjCpf.length === 11) {
		    return cnpjCpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3-\$4");
		  } 
		  return cnpjCpf.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, "\$1.\$2.\$3/\$4-\$5");
		}
		
		
		